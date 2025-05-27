import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String username;
        if(args.length>0){
            username = args[0];
        }else{
            Scanner scanner = new Scanner(System.in);
            System.out.print("github-activity ");
            username = scanner.nextLine();
            scanner.close();
        }

        System.out.println("Output: ");

        ArrayList<String > results = GitFetcher.getActivityFromUser(username);
        if(results.size() == 0){
            System.out.println("User not found");
        }else{
            for(String act : results){
                System.out.println(act);
            }
        }

    }
}