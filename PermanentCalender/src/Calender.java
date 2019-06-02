
import java.util.Scanner;
public class Calender{
    int year = 2019;
    int month = 6;
    int weekday = 0;
    int sum = 0;

    /**
     *  input
     */
    public void input(){
        Scanner A = new Scanner(System.in);
        System.out.println("请分别输入月份和年份：");
        String input = A.nextLine();
        String[] strArr = input.split(" ");

        if(strArr.length == 2){
            month = Integer.parseInt(strArr[0]);
            year = Integer.parseInt(strArr[1]);
        } else if (strArr.length ==1 && (!strArr[0].isEmpty())){
            year = Integer.parseInt(strArr[0]);
        }
    }

    /**
     * calculate
     */
    public void calculate(){
        sum = 0;
        for (int i = 1900; i < year; i++) {
            if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {
                sum = sum + 366;
            } else {
                sum = sum + 365;
            }
        }
        for (int i = 1; i < month; i++) {
            if (i == 2) {
                if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
                    sum = sum + 29;
                } else {
                    sum = sum + 28;
                }
            } else {
                if (i == 4 || i == 6 || i == 9 || i == 11) {
                    sum += 30;
                } else {
                    sum += 31;
                }
            }

        }
        sum = sum + 1;
        weekday = sum % 7;
    }

    /**
     * display
     */
    public void display(){
        System.out.println("日\t一\t二\t三\t四\t五\t六");
        for(int i=1;i<=weekday;i++){
            System.out.print("\t");
        }
        int f=0;
        if(month==4||month==6||month==9||month==11){
            f=30;
        } else if(month==1||month==3||month==5||month==7||month==8||month==10||month==12){
            f=31;
        } else if(month==2){
            if(year%4==0&&year%100!=0||year%400==0){
                f=29;
            } else{
                f=28;
            }
        } else {
            System.out.println("请输入正确月份：");
        }
        for(int i=1;i<=f;i++){
            if(sum%7==6){
                System.out.print(i+"\n");
            }else{
                System.out.print(i+"\t");
            }
            sum++;
        }
    }

}

 /*public class Calender {

    //以下程序段是用来计算输入日期为该年的第几天的

    public static int cptDay(int year , int month , int day){

        byte dayadd[]={1,-2,1,0,1,0,1,1,0,1,0,1};  //用来存储每个月天数和30的差值

        int daycount = 0;  //这是天数daycount计数器，初始化为0

        for(int i=0; i<month-1; i++)

            daycount+=(30+dayadd[i]);

        daycount+=day;

        return (month>2)?daycount+isLeap(year):daycount;

    }

    //闰年判定程序段，闰年返回1，平年返回0

    public static int isLeap(int year){

        if((year%400==0)||((year%4==0)&&(year%100!=0)))

            return 1;

        return 0;

    }

    //计算输入日期是星期几

    //采用了基姆拉尔森计算公式

    //W= (d+2*m+3*(m+1)/5+y+y/4-y/100+y/400) mod 7

    //在公式中d表示日期中的日数，m表示月份数，y表示年数。

    //注意：在公式中有个与其他公式不同的地方：

    //把一月和二月看成是上一年的十三月和十四月，例：如果是2004-1-10则换算成：2003-13-10来代入公式计算。

    public static int getWeek(int year,int month,int day){

        if(month<3)

        { month+=12; year--;}

        return (day+2*month+3*(month+1)/5+year+year/4-year/100+year/400)%7;

    }

    //以下程序段是来计算输入日期为该年第几周的

    public static int weekCount(int year,int month,int day){

        int dayCnt = cptDay(year,month,day);

        int weekminus = getWeek(year,month,day)-getWeek(year,1,1);

        int weekCnt = 0;

        if(dayCnt%7==0) weekCnt = dayCnt/7+((weekminus>0)?1:0);

        else weekCnt = dayCnt/7+((weekminus>0)?2:1);

        return weekCnt;

    }

    //打印万年历

    public static void printCal(int year){

        byte dayadd[]={0,1,-2,1,0,1,0,1,1,0,1,0,1}; //同样的，每月天数和30的差值，注意，dadadd[0]的0并没用到

        int wkpoint = getWeek(year,1,1);      //wkpoint用来指出当前日期的星期数

        int t = 0;                 //t用来作为一个标记器，解决闰年2月有29天的问题

        int bk = 0;                 //bk用来记录需输空白的数目

        String week[]={"一","二","三","四","五","六","日"};

        for(int i=1;i<13;i++)

        {

            t = 0;

            bk = 0;

            if((i==2)&&(isLeap(year)==1))

                t = 1;               //当且仅当闰年的2月份才将其置为1

            System.out.println("\n\n\t\t"+year+" 年 "+i+" 月\n");

            for(int j=0;j<7;j++)

                System.out.print(week[j]+"\t");

            System.out.println();

            while(bk++<wkpoint)

                System.out.print('\t');

            for(int j=1;j<=(30+dayadd[i]+t);j++)

            {

                System.out.print(j+"\t");      //循环输出每月日期

                if(wkpoint==6)

                { wkpoint = 0; System.out.print('\n');} //当wkpoint计数器为6时将其置为0，并换行

                else

                    wkpoint++;

            }

        }
    }

    public static void main(String[] args){

        String week[]={"一","二","三","四","五","六","日"};

        System.out.println("输入的日期是该年的第"+cptDay(2009,2,15)+"天");

        System.out.println("这一天是该年的第"+weekCount(2009,2,15)+"周 "+week[getWeek(2009,2,15)]);

        printCal(2009);

    }

}*/