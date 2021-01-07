package core;

import com.calendarfx.model.Interval;
import net.fortuna.ical4j.model.DateTime;
import org.apache.commons.lang3.tuple.Pair;
import persist.FactoryDAOImpl;
import persist.MeetingDAO;
import persist.InvitationDAO;
import core.SessionFacade;
import persist.RoomDAO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.*;

public class ReservationFacade {

    private User connectedUser;
    private FactoryDAOImpl factoryDAO;
    private MeetingDAO meetingDAO;
    private RoomDAO roomDAO;

    public static RoomTopicFacade topicFacade = new RoomTopicFacade();

    /**
     * Default constructor
     */
    public ReservationFacade() {
        this.factoryDAO = FactoryDAOImpl.getInstance();
        this.meetingDAO = factoryDAO.createMeetingDAO();
        this.roomDAO = factoryDAO.createRoomDAO();
    }

    public boolean createMeeting(LocalDate dateBegin, LocalTime hourBegin, LocalDate dateEnd, LocalTime hourEnd, String meetingTopic){
        User creator = SessionFacade.getConnectedUser();
        String creatorUsername = creator.getUserName();
        boolean check = false;
        if (creator.getIsManager()){
            check = meetingDAO.insert(dateBegin,hourBegin,dateEnd,hourEnd,creatorUsername,meetingTopic);
        }
        else {
            check = meetingDAO.insertWaitingMeeting(dateBegin,hourBegin,dateEnd,hourEnd,creatorUsername,meetingTopic);
        }

        return check;
    }

    public boolean createMeetingWithRoom(int idMeeting, String nameRoom){
        User creator = SessionFacade.getConnectedUser();
        String creatorUsername = creator.getUserName();
        boolean check = false;
        if (creator.getIsManager()){
            check = meetingDAO.insertMeetingWithRoom(idMeeting,nameRoom);
        }
        else {
            check = meetingDAO.insertWaitingMeetingWithRoom(idMeeting,nameRoom);
        }

        return check;
    }

    public int createMeetingAndGetId(LocalDate dateBegin, LocalTime hourBegin, LocalDate dateEnd, LocalTime hourEnd, String meetingTopic){
        User creator = SessionFacade.getConnectedUser();
        String creatorUsername = creator.getUserName();
        int idMeeting = -1;
        if (creator.getIsManager()){
            idMeeting = meetingDAO.insertAndGetId(dateBegin,hourBegin,dateEnd,hourEnd,creatorUsername,meetingTopic);
        }
        else {
            idMeeting = meetingDAO.insertWaitingMeetingAndGetId(dateBegin,hourBegin,dateEnd,hourEnd,creatorUsername,meetingTopic);
        }

        return idMeeting;
    }

    public Meeting findMeetingById(int id){
        return meetingDAO.findByID(id);
    }

    public void deleteMeeting(int id){
        meetingDAO.deleteMeeting(id,SessionFacade.getConnectedUser().getIsManager());
    }

    public Collection<String> getAvailableRooms(int capacity, Meeting m){

        RoomTopicFacade rtfacade = new RoomTopicFacade();

        Collection<String> res = rtfacade.getRooms();
        HashMap<Integer,String> takenRooms = roomDAO.getAllTakenRooms();

        for (String i : res){
            if(rtfacade.displayRoomByName(i).getCapacity() >= capacity) {
                for (Integer j : takenRooms.keySet()) {
                    if (i.equals(takenRooms.get(j))) {
                        Meeting m2 = findMeetingById(j);
                        if (isAtTheSameTime(m, m2)) {
                            res.remove(i);
                        }
                    }
                }
            }
        }


        return res;
    }

    private boolean isAtTheSameTime(Meeting m1, Meeting m2){

        boolean res = false;

        LocalDate dD1 = m1.getDateBegin();
        LocalDate dF1 = m1.getDateEnd();
        LocalTime hD1 = m1.getHourBegin();
        LocalTime hF1 = m1.getHourEnd();

        LocalDateTime deb1 = dD1.atTime(hD1);
        LocalDateTime fin1 = dF1.atTime(hF1);

        LocalDate dD2 = m2.getDateBegin();
        LocalDate dF2 = m2.getDateEnd();
        LocalTime hD2 = m2.getHourBegin();
        LocalTime hF2 = m2.getHourEnd();

        LocalDateTime deb2 = dD2.atTime(hD2);
        LocalDateTime fin2 = dF2.atTime(hF2);

        if(deb1.isBefore(deb2) && fin1.isAfter(deb2)){
            res = true;
        }
        else if (deb1.isBefore(fin2) && fin1.isAfter(fin2)){
            res = true;
        }

        return res;
    }
/*
    public ArrayList<Room> findRoomByEquipment(ArrayList<String> equipments){
        ArrayList<Room> listRooms = new ArrayList<>();
        ArrayList<String> listRoom = roomDAO.findAll();
        for(int i = 0 ; i < listRoom.size() ; i++){
            Room room = roomDAO.findBy(listRoom.get(i));
            if(!roomContainAllEquipment(equipments, room)){
                listRoom.remove(room);
            }
        }

        for (int i = 0 ; i < listRoom.size() ; i++){
            listRooms.add(roomDAO.findBy(listRoom.get(i)));
        }
        return listRooms;
    }

    private boolean roomContainAllEquipment (ArrayList<String> listEquipment, Room room){
        boolean res = true;
        boolean find = false;
        int i = 0;
        int j;
        while (i<listEquipment.size() & res){
            j=0;
            while (j<room.getEquipment().size() & !find){
                if (listEquipment.get(i).equals(room.getEquipment().get(j).getKey())){
                    find = true;
                }
                j++;
            }
            if (j==room.getEquipment().size()){
                res = false;
            }
            i++;

        }
        return res;
    }

 */
public HashMap<Integer,ArrayList<String>> createAutoSchedule(HashMap<String,Integer> matieres, LocalDate dateDebut, LocalDate dateFin) throws Exception {

    int nbHeures = 0;
    for ( int i  : matieres.values()){
        nbHeures += i;

    }

    HashMap<Integer,ArrayList<String>> uDays = usableDaysBetween(dateDebut,dateFin);
    int hDisp = 0;
    for (Integer i : uDays.keySet()){
        if(i > 0){
            hDisp += 8;
        }
    }
    if(hDisp<nbHeures) throw new Exception();


    ArrayList<String> mat = new ArrayList<>();

    for (String i : matieres.keySet()){
        for(int j = 0; j < matieres.get(i); j++){
            mat.add(i);
        }
    }

    int nbCren = hDisp / 4;

    int min;

    if((float) hDisp/nbHeures <1.2){
        min = 4;
    } else if((float) hDisp/nbHeures < 2){
        min = 3;
    } else {
        min = 2;
    }

    int cpt = 1;
    int pair = 0;
    ArrayList<String> m = new ArrayList<>();

    for (int i = 0; i < nbCren; i++){
        ArrayList<String> choixCren = new ArrayList<>();

        if(mat.size() > 2) {
            int nombreHeuresCren = min + (int) (Math.random() * ((4 - min) + 1));
            if(nombreHeuresCren < mat.size()){
                for (int j = 0; j < nombreHeuresCren; j++) {
                    int cours = (int) (Math.random() * ((mat.size() - 1) + 1));
                    choixCren.add(mat.get(cours));
                    mat.remove(cours);
                }
            } else{
                choixCren = mat;
            }
        } else if (!mat.isEmpty()){
            choixCren = mat;
        }

        if(pair%2 == 0){
            m = triCreneau(choixCren,i);
        } else {
            ArrayList<String> n = triCreneau(choixCren,i);
            m.addAll(n);
            uDays.put(cpt, m);
            cpt += 1;
        }

        pair += 1;


    }
    return uDays;


}
    public Collection<Meeting> autoSchedule(HashMap<String,Integer> matieres,LocalDate dB,LocalDate dE) throws Exception{
        HashMap<Integer,ArrayList<String>> uDays = createAutoSchedule(matieres,dB,dE);

        Collection<Meeting> meetings = new ArrayList<>();
        if(uDays.isEmpty()){
             meetings = autoSchedule(matieres,dB,dE);
        }
        else {
            LocalDate date = dB;
            for(Integer i : uDays.keySet()){
                if(i>0 && !(uDays.get(i).isEmpty())) {
                    for (int j = 0; j < 8; j++) {
                        if (!(Objects.equals(uDays.get(i).get(j), "X"))) {
                            LocalTime hD;
                            switch (j) {
                                case 1:
                                    hD = LocalTime.of(9, 00);
                                    break;
                                case 2:
                                    hD = LocalTime.of(10, 00);
                                    break;
                                case 3:
                                    hD = LocalTime.of(11, 00);
                                    break;
                                case 4:
                                    hD = LocalTime.of(14, 00);
                                    break;
                                case 5:
                                    hD = LocalTime.of(15, 00);
                                    break;
                                case 6:
                                    hD = LocalTime.of(16, 00);
                                    break;
                                case 7:
                                    hD = LocalTime.of(17, 00);
                                    break;
                                case 0:
                                default:
                                    hD = LocalTime.of(8, 00);
                                    break;
                            }
                            LocalTime hF = hD.plusMinutes(55);

                            Topic topic = topicFacade.getTopicByName(uDays.get(i).get(j));
                            String username = SessionFacade.getConnectedUser().getUserName();

                            Meeting m = new Meeting(date, hD, date, hF, topic, username);

                            meetings.add(m);
                        }
                    }
                }
                date = date.plusDays(1);
            }
        }

        return meetings;
    }

    public ArrayList<String> triCreneau(ArrayList<String> cours, int cren){

        String [] res = new String [4];
        int size = cours.size();
        for (int i = 0; i < size-1; i ++){
            for(int j = i+1; j < size; j ++){
                if (cours.get(i).equals(cours.get(j))){
                    Collections.sort(cours);
                }
            }
        }
        if (cren%2 == 0){
            for (int i = 3; i >= 0; i--) {
                if(!cours.isEmpty()) {
                    res[i] = cours.get(0);
                    cours.remove(0);
                } else {
                    res[i] = "X";
                }
            }

        } else {
            for(int i = 0; i < 4; i++){
                if(!cours.isEmpty()) {
                    res[i] = cours.get(0);
                    cours.remove(0);
                } else {
                    res[i] = "X";
                }
            }
        }


        return new ArrayList<>(Arrays.asList(res).subList(0, 4));
    }

    public HashMap<Integer,ArrayList<String>> usableDaysBetween(LocalDate dateD, LocalDate dateF){

        HashMap<Integer,ArrayList<String>> res = new HashMap<>();

        LocalDateTime d = dateD.atStartOfDay();

        int nbJours = daysBetween(dateD,dateF);
        int fDay;

        String z = String.valueOf(d.getDayOfWeek());

        switch (z) {
            case "MONDAY":
                fDay = 0;
                break;
            case "TUESDAY":
                fDay = 1;
                break;
            case "WEDNESDAY":
                fDay = 2;
                break;
            case "THURSDAY":
                fDay = 3;
                break;
            case "FRIDAY":
                fDay = 4;
                break;
            case "SATURDAY":
                fDay = 5;
                break;
            default:
                fDay = 6;
                break;
        }
        int i = 1;
        int k = i + fDay;
        while (i<nbJours+1){

            if(k==8){ k = 1; }
            if(k<6){
                ArrayList<String> hours = new ArrayList<>();
                res.put(i,hours);
            }
            else {
                ArrayList<String> hours = new ArrayList<>();
                res.put(-i,hours);
            }
            i += 1;
            k += 1;
        }

        return res;
    }

    public int daysBetween(LocalDate d1, LocalDate d2) {
        Period period = Period.between(d1,d2);
        return period.getDays();
    }



}
