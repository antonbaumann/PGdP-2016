package pgdpws16;

public class Date {
    private int weekday;
    private int starthour;
    private int startmin;
    private int duration;
    private String title;
    
    public Date(int weekday, int starthour, int startmin, int duration, String title){
        /*
        we would need to check whether the parameters are valid;
        if not we would throw an exception (will be considered later in the lecture)
        */
        this.weekday = weekday;
        this.starthour = starthour;
        this.startmin = startmin;
        this.duration = duration;
        this.title = title;
    }
    
    public int getWeekday(){
        return weekday;
    }
    
    public int getStarthour(){
        return starthour;
    }
    
    public int getStartmin(){
        return startmin;
    }
    
    public int getDuration(){
        return duration;
    }
    
    public String getTitle(){
        return title;
    }
    
    
    
    
    public static String weekdaySpoken(int n){
        switch(n){
            case 0: return "Monday";
            case 1: return "Tuesday";
            case 2: return "Wednesday";
            case 3: return "Thursday";
            case 4: return "Friday";
            case 5: return "Saturday";
            case 6: return "Sunday";
            default: return "Invalid Input";           
        }
    }
    
    private String durationReadable(){
        return durationReadable(duration);
    }
    
    private String durationReadable(int time){
        if(time > 24*60)
            return (time / (24*60)) + " days " + durationReadable(time % (24*60));
        if(time > 60)
            return (time/60) + " hours " + durationReadable(time % 60);
        return time + " minutes";
    }
    
    @Override
    public String toString(){
        return title + " at " + weekdaySpoken(weekday) + ", " + starthour + ":" + startmin + ", duration: " + durationReadable();
    }
    
    
}
