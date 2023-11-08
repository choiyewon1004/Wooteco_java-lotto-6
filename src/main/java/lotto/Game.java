package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.*;
import java.util.stream.Collectors;

public class Game {
    private int money, count;
    private List<Lotto> buy = new ArrayList<>();
    private Lotto answer; private int bonus;
    private HashMap<String, Integer> correct = new HashMap<>();

    private void start(){
        System.out.println("구입 금액을 입력해 주세요.");
        this.money = Integer.parseInt(Console.readLine());
        this.count = this.money/1000;
    }

    private void generate(){
        System.out.println(this.count+"개를 구매했습니다.");
        for(int i=0;i<this.count;i++){
            //로또 생성
            this.buy.add(new Lotto(random()));
            this.buy.get(i).printInfo();
        }
    }

    private List<Integer> random(){
        return Randoms.pickUniqueNumbersInRange(1,45,6);
    }

    private void userWinning(){
        System.out.println();
        System.out.println("당첨 번호를 입력해 주세요.");
        List<Integer> list = Arrays.stream(Console.readLine().split(","))
                .mapToInt(Integer::parseInt)
                .boxed().collect(Collectors.toList());
        this.answer = new Lotto(list);

    }

    private void userBonus(){
        do{
            System.out.println();
            System.out.println("보너스 번호를 입력해 주세요.");
            this.bonus = Integer.parseInt(Console.readLine());
        }while(!checkRange());
    }

    private boolean checkRange(){
        return (this.bonus >0 ) &(this.bonus<46);
    }

    private void initHash(){
        //System.out.println("initHash");
        corType[] arr = corType.values();

        for (corType rb : arr) {
            //System.out.println(rb);
            this.correct.put(rb.toString(),0);
        }
    }

    private void checkLottos(){
        initHash();
        for(int i=0;i<this.count;i++){
            // 중복 여부 확인
            checkLotto(this.buy.get(i).compareOther(this.answer), this.buy.get(i));
        }
    }
    private void checkLotto(int check_count, Lotto here){
        if(check_count ==3){this.correct.replace("THREE" , this.correct.get("THREE")+1); }
        if(check_count ==4){this.correct.replace("FOUR" , this.correct.get("FOUR")+1);}
        if(check_count ==5){
            this.correct.replace("FIVE_V1" , this.correct.get("FIVE_V1")+1);
            checkFive(here);
        }
        if(check_count ==6){this.correct.replace("SIX" , this.correct.get("SIX")+1); }

    }

    private void checkFive(Lotto here){
        if(here.containNum(this.bonus)){
            this.correct.replace("FIVE_V1" , this.correct.get("FIVE_V1")-1);
            this.correct.replace("FIVE_V2" , this.correct.get("FIVE_V2")+1);
        }
    }

    private double result(){
        double res_result=0;
        checkLottos();
        res_result = (this.correct.get("THREE")*5000)+(this.correct.get("FOUR")*50000)+(this.correct.get("FIVE_V1")*1500000)+(this.correct.get("FIVE_V2")*30000000)+(this.correct.get("SIX")*2000000000);
        return ((double)res_result/(double)this.money)*100;
    }

    private void printResult(){
        double res_result = result();
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");

        corType[] arr = corType.values();

        for (corType rb : arr) {
            System.out.println( rb.getValue()+this.correct.get(rb.toString())+"개");
//            this.correct.put(rb.toString(),0);
        }
        System.out.println("총 수익률은 "+String.format("%.1f", res_result) +"%입니다.");
    }

    public void DoIt(){
        start();
        generate();
        userWinning();
        userBonus();
        printResult();
    }
}
