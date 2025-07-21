public class DayTwelve2024 {
    private static final char[][] garden = DataLoader.generateCharGrid("DataFiles\\DayTwelveTestData.txt");
    public static void main(String[] args) {
        for(char[] row : garden){
            for(char c : row){
                System.out.print(c);
            }
            System.out.println();
        }
    }
}
