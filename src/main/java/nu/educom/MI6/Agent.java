package nu.educom.MI6;

public class Agent {
    private int serviceNumber = 1000;
    private String passPhrase = "For ThE Royal QUEEN";
    private boolean blackList = false;

    public Agent(){
//        this.serviceNumber = 1000;
        System.out.println("New agent created, servicenr: " + this.serviceNumber);
    }

    public void setBlackList(boolean bool){
        this.blackList = bool;
    }
    public void setServiceNumber(int serNum){
        this.serviceNumber = serNum;
    }

    public String getServiceNumber(){
        String serviceNumber = "";
        if(this.serviceNumber < 100 && this.serviceNumber > 9){
            serviceNumber = "0" + this.serviceNumber;
        }else if(this.serviceNumber < 10){
            serviceNumber = "00" + this.serviceNumber;
        }else{
            serviceNumber += this.serviceNumber;
        }
        return serviceNumber;
    }

    public boolean validateNumber(){
//        System.out.println("<< VALIDATION >>");

        if(this.serviceNumber < 1 || this.serviceNumber > 956){
//            System.out.println("Number validation: TRUE");
            return true;
        } else{
//            System.out.println("Number validation: FALSE");
            return false;
        }
    }

    public boolean validateSecretPhrase(String input){
        System.out.println("input: |" + input + "|");
        System.out.println("pass: |" + this.passPhrase + "|");
        if(input.equals(this.passPhrase)){
            System.out.println("TRUE");
            return true;
        } else{
            System.out.println("FALSE");
            return false;
        }
    }

    public boolean isNumeric(String str)
    {
        for (char c : str.toCharArray())
        {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }
}
