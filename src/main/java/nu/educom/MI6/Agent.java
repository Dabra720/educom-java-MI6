package nu.educom.MI6;

public class Agent {
    private int serviceNumber;
    private String passPhrase;
    private boolean blackList = false;

    public Agent(int serNum){
        serviceNumber=serNum;
    }

    public String getServiceNumber(){
        String serviceNumber = "";
        if(this.serviceNumber < 100 && this.serviceNumber > 9){
            if(this.serviceNumber < 10){
                serviceNumber="00"+this.serviceNumber;
            }
            serviceNumber="0"+this.serviceNumber;
        }

        return serviceNumber;
    }

    public boolean validateNumber(int serNum){
        if(serNum > 0 && serNum < 957){
            return true;
        } else{
            return false;
        }
    }
}
