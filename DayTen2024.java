
public class DayTen2024 {
    private static final Integer[][] topographicMap = GridGenerator.generateIntegerGrid("DataFiles\\DayTenTestData.txt");
    public static void main(String[] args) {
        for(Integer[] row : topographicMap){
            for(Integer i : row){
                System.out.print(i);
            }
            System.out.println();
        }
    }
}
