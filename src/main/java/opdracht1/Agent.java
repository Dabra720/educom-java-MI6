package opdracht1;

public class Agent {
    private final String serviceNumber;
    private boolean loggedIn = false;
    private String passPhrase = "For ThE Royal QUEEN";

    public Agent(String serviceNumber){
        this.serviceNumber = convertNumber(serviceNumber);
        System.out.println("New agent created, servicenr: " + this.serviceNumber);
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

    public String getServiceNumber(){
        return this.serviceNumber;
    }
    public String convertNumber(String serviceNumber){

        int serNum = Integer.parseInt(serviceNumber);
        if(serNum < 100 && serNum > 9){
            serviceNumber = "0" + serNum;
        }else if(serNum < 10){
            serviceNumber = "00" + serNum;
        }
        return serviceNumber;
    }

}