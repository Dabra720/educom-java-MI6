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

    public void setPassPhrase(String newPass){
        this.passPhrase = newPass;
    }
    public boolean matchPassword(String input){
        return this.passPhrase.equals(input);
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
//    public String convertNumber(String serviceNumber){
//
//        int serNum = Integer.parseInt(serviceNumber);
//        if(serNum < 100 && serNum > 9){
//            serviceNumber = "0" + serNum;
//        }else if(serNum < 10){
//            serviceNumber = "00" + serNum;
//        }
//        return serviceNumber;
//    }

}
