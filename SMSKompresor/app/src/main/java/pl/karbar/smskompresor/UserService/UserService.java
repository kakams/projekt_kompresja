package pl.karbar.smskompresor.UserService;

/**
 * Created by Karol on 2015-01-05.
 */
public class UserService {
    public String getNameById(String id){
        //TODO
        return "Name"+id;
    }

    public String getPhoneById(String id){
        //TODO
        return Integer.toString(600700800+Integer.parseInt(id));
    }
}
