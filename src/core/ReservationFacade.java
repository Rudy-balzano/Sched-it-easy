package core;

import com.calendarfx.model.Interval;
import org.apache.commons.lang3.tuple.Pair;
import persist.FactoryDAOImpl;
import persist.MeetingDAO;
import persist.InvitationDAO;
import core.SessionFacade;
import persist.RoomDAO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

public class ReservationFacade {

    private User connectedUser;
    private FactoryDAOImpl factoryDAO;
    private MeetingDAO meetingDAO;
    private RoomDAO roomDAO;

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




}
