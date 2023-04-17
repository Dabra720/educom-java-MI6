package nu.educom.MI6;

public class Agent {
    private final int serviceNumber;
    private boolean loggedIn = false;
    private String passPhrase = "For ThE Royal QUEEN";
//    private List<LoginAttempt> loginAttemptList;

    public Agent(int serviceNumber){
        this.serviceNumber = serviceNumber;
//        this.serviceNumber = convertNumber(serviceNumber);
        System.out.println("New agent created, servicenr: " + getFormattedServiceNumber());
    }

    public String getPassPhrase(){
        return passPhrase;
    }
    public void setPassPhrase(String newPass){
        this.passPhrase = newPass;
    }

    public void setLoggedIn(boolean bool){
        this.loggedIn = bool;
    }
    public boolean isLoggedIn(){
        return this.loggedIn;
    }

    public int getServiceNumber(){
        return this.serviceNumber;
    }

    public String getFormattedServiceNumber(){
        return String.format("%03d",serviceNumber);
    }


}
