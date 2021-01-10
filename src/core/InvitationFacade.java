package core;

import javafx.beans.property.StringProperty;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import persist.*;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Class that represents a facade for an invitation
 * @author emilie jean
 * @version 1.0
 * @see gui.controllers.InvitationController
 * @see gui.controllers.InvitePeopleController
 * @see gui.controllers.SeeInvitedPeopleController
 */
public class InvitationFacade {
    /**
     * userDAO
     */
    private final UserDAO userDAO;
    /**
     * invitationDAO
     */
    private final InvitationDAO invitationDAO;
    /**
     * groupDAO
     */
    private final GroupDAO groupDAO;
    /**
     * Represents the username of the connected user
     */
    private String u;
    /**
     * equipmentDAO
     */
    private final EquipmentDAO equipmentDAO;
    /**
     * meetingDAO
     */
    private final MeetingDAO meetingDAO;
    /**
     * roomDAO
     */
    private final RoomDAO roomDAO;

    /**
     * Constructor of InvitationFacade
     * Use of FactoryDAO
     * @see SessionFacade
     */
    public InvitationFacade(){
        FactoryDAOImpl factoryDAO = FactoryDAOImpl.getInstance();
        this.userDAO = factoryDAO.createUserDAO();
        this.invitationDAO = factoryDAO.createInvitationDAO();
        this.groupDAO = factoryDAO.createGroupDAO();
        this.equipmentDAO = factoryDAO.createEquipmentDAO();
        this.meetingDAO = factoryDAO.createMeetingDAO();
        this.roomDAO = factoryDAO.createRoomDAO();
        this.u = SessionFacade.getConnectedUser().getUserName();
    }

    /**
     * Function used to create a new invitation, returns a boolean
     * @param invitedUsername
     * @param state
     * @param idMeeting
     * @return True if the invitation has been created, false if not.
     */
    public boolean createInvitationForUser (String invitedUsername, int state, int idMeeting){
        return invitationDAO.insert(invitedUsername, state, idMeeting);
    }

    public boolean createInvitationForGroup (String invitedGroupName, int state, int idMeeting){
        boolean res1 = false;
        Collection<String> users = userDAO.findAllByGroup(invitedGroupName);
        for (String user : users){
            res1 = createInvitationForUser(user, state, idMeeting);
            if (!res1){
                return res1;
            }
        }
        boolean res2 = false;
        res2 = invitationDAO.insertInvitationGroup(invitedGroupName, idMeeting);

        if (res1 & res2){
            return true;
        }else {
            return false;
        }

    }

    /**
     * Display all the invitations for this username
     * @return an arrayList of all invitations for this username
     */
    public ArrayList<Invitation> getAllInvitation(){
        return invitationDAO.findAllInvitation(u);
    }

    /**
     * Function used to decline an invitation, say that we will be absent at the invitation's meeting.
     * @param username
     * @param id
     */
    public void declineInvitation(String username, int id){
        userDAO.declineWaitingInvitation(username, id);
    }

    /**
     * Function used to accept an invitation, say that we will be present at the invitation's meeting.
     * @param username
     * @param id
     */
    public void acceptInvitation(String username, int id){
        userDAO.acceptWaitingInvitation(username, id );
    }

    /**
     * Used to know all the invited users for a meeting
     * @param meetingId
     * @return all the usernames of the invited users for a meeting
     */
    public Collection<String> getAllInvitedUsers(int meetingId){
        return invitationDAO.getInvitedUsers(meetingId);
    }

    /**
     * used to get all the users
     * @return every usernames of every users
     */
    public ArrayList<String> getAllUsers() {
        ArrayList<String> listUsers = new ArrayList<>();
        listUsers.addAll(userDAO.findAllRegUsersNames());
        listUsers.addAll(userDAO.findAllManagersNames());
        return listUsers;
    }

    /**
     * used to get all the invited groups
     * @param idMeeting
     * @return the name of every group invited
     */
    public ArrayList<String> getAllInvitedGroups(int idMeeting){
        return invitationDAO.getInvitedGroups(idMeeting);
    }

    /**
     * used to get all the existing groups
     * @return every names of every groups existing
     */
    public ArrayList<String> getAllGroups(){
        ArrayList<String> listGroups = new ArrayList<>();
        listGroups.addAll(groupDAO.findAll());
        return listGroups;
    }

    /**
     * used to get the rented equipment for a meeting and a username
     * @return a Collection of Equipments
     */
    public Collection<Equipment> getRentedEquipment(int idMeeting){
        return equipmentDAO.getRentedEquipment(SessionFacade.getConnectedUser().getUserName(), idMeeting);
    }

    /**
     * Create the string to display on the notification panel
     * @param equipment
     * @param idMeeting
     * @return a String with the id Meeting and the cost
     */

    public String notifFacture(Collection<Equipment> equipment, int idMeeting){

        Meeting m = meetingDAO.findByID(idMeeting);

        double prixTot = 0.0;

        for(Equipment e : equipment){
            prixTot += e.getPrice();
        }

        return "Equipment for meeting number : "+ m.getId() +" the : "+m.getDateBegin()+", cost : "+prixTot+" euros.";
    }

    /**
     * Used to get all the meetings with rooms a user created
     * @return A collection of id meetings
     */
    public Collection<Integer> allMeetingsWithRoom(){
        Collection<Meeting> m = meetingDAO.findAllCreatedMeetings(SessionFacade.getConnectedUser().getUserName());
        HashMap<Integer,String> r = roomDAO.getAllTakenRooms();
        Collection<Integer> res = new ArrayList<>();

        for(Integer i : r.keySet()){
            for(Meeting j : m){
                if (i == j.getId()){
                    res.add(i);
                }
            }
        }
        return res;
    }

    /**
     * Find in the database the invitation for a user and a meeting
     * @param idMeeting
     * @return an invitation
     */

    public Invitation findInvitation(int idMeeting){

        Invitation res = new Invitation();
        Collection<Invitation> invitations = invitationDAO.findInvitation(SessionFacade.getConnectedUser().getUserName());

        for(Invitation i : invitations){
            if(i.getMeetingInvitation().getId() == idMeeting){
                res = i;
            }
        }
        return res;
    }

    /**
     * Generate a bill in pdf
     * @param equipments
     * @param idMeeting
     * @throws IOException
     */

    public void facture(Collection<Equipment> equipments, int idMeeting) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        contentStream.beginText();

        contentStream.setFont(PDType1Font.COURIER, 20);
        contentStream.setLeading(14.5f);
        contentStream.newLineAtOffset(25, 700);
        contentStream.showText("Bill for equipment reservation : ");
        contentStream.newLine();
        contentStream.newLine();
        contentStream.newLine();
        contentStream.newLine();

        contentStream.setFont(PDType1Font.COURIER, 12);

        for (Equipment e : equipments){
            contentStream.newLine();
            contentStream.showText("    "+e.getName()+" : "+e.getPrice());
            contentStream.newLine();
        }
        contentStream.newLine();
        contentStream.newLine();
        contentStream.newLine();
        contentStream.newLine();
        contentStream.newLine();
        contentStream.showText(notifFacture(equipments,idMeeting));
        contentStream.endText();
        contentStream.close();

        document.save("BillForMeeting"+idMeeting+"_"+SessionFacade.getConnectedUser().getUserName()+".pdf");
        document.close();
    }
}
