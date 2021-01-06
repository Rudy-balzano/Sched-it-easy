package core;

import org.apache.commons.lang3.tuple.Pair;
import persist.FactoryDAOImpl;
import persist.MeetingDAO;
import persist.InvitationDAO;
import core.SessionFacade;
import persist.RoomDAO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

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
