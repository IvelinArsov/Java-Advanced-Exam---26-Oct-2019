import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DateApp {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);



        ArrayDeque<Integer> male = new ArrayDeque<>(); // инциализиране на стек
        Arrays.stream(scan.nextLine()
                .split("\\s++"))
                .map(Integer::parseInt)
                .forEach(male::push);

        ArrayDeque<Integer> female = Arrays.stream(scan.nextLine() // инциализиране на опашка
                .split("\\s++"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));
        int matchesCount = 0;
        while(!male.isEmpty() && !female.isEmpty()){
            int currentMale = male.peek();
            if(currentMale <= 0){
                male.pop();
                continue;
            }
            if(currentMale % 25 == 0){
                male.pop();
                male.pop();
                continue;
            }
            int currentFemale = female.poll();
            if(currentFemale <= 0){
                continue;
            }
            if(currentFemale % 25 == 0){
                female.poll();
                continue;
            }
            if(currentMale == currentFemale){
                matchesCount++;
                male.pop();
            }else{
                male.push(male.pop()-2);
            }
        }

        StringBuilder builder = new StringBuilder();
        builder.append("Matches: ").append(matchesCount).append(System.lineSeparator());
        if (male.isEmpty()) {
            builder.append("Males left: none").append(System.lineSeparator());
        }else{
            builder.append("Males left: ");
            builder.append(male.stream().map(String::valueOf).collect(Collectors.joining(", ")));
            builder.append(System.lineSeparator());
        }
        if (female.isEmpty()) {
            builder.append("Females left: none").append(System.lineSeparator());
        }else{
            builder.append("Females left: ");
            builder.append(female.stream().map(String::valueOf).collect(Collectors.joining(", ")));
            builder.append(System.lineSeparator());
        }
        System.out.println(builder);


    }
}
