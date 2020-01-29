package com.bss.re.restapi.components;

import ch.obermuhlner.math.big.BigFloat;
import com.bss.re.domain.dto.CurrentUserSimilar;
import com.bss.re.domain.dto.UserBook;
import com.bss.re.domain.dto.UserSimilar;
import com.bss.re.domain.model.Re;
import lombok.extern.log4j.Log4j;

import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description 基于协同过滤推荐算法
 * @Date 2019/5/30
 * @Param
 * @return
 *Created by QAQ on 2019/5/30
 */

@Log4j
public class RecommendationUtils {

    public static double calcSimilarityBetweenUsers(UserBook A, UserBook B){
        ArrayList<Integer> a = new ArrayList<>();
        ArrayList<Integer> b = new ArrayList<>();

        int i;
        Set<String> books = new HashSet<>();

        for (Re re: A.getBooks()){
             books.add(re.getIsbn());
        }

        for (Re re: B.getBooks()){
            books.add(re.getIsbn());
        }

        for (String index:books){
           a.add(0);
           b.add(0);
        }

        // 给数组a赋值
        for (Re re: A.getBooks()){
            i=0;
            for (String book: books){
                if (book.equals(re.getIsbn())){
                    a.set(i,1);
                }
                i++;
            }
        }

        // 给数组b赋值
        for (Re re: B.getBooks()){
            i=0;
            for (String book: books){
                if (book.equals(re.getIsbn())){
                    b.set(i,1);
                }
                i++;
            }
        }

        return cos(a,b);
    }

    /*
     * @Description 计算余弦
     * @Date 2019/5/30
     * @Param [A, B]
     * @return double
     **/
    public static double cos(ArrayList<Integer> A,ArrayList<Integer> B){

        BigFloat.Context context = BigFloat.context(new MathContext(50, RoundingMode.HALF_UP));

        BigFloat molecule = context.valueOf(0); // 分子
        BigFloat denominator;
        BigFloat sunB = context.valueOf(0);
        BigFloat sunA = context.valueOf(0);
        if (A.size() == 1)
            return 1;

        for (int i = 0; i < A.size(); i++){
             BigFloat a = context.valueOf(A.get(i));
             BigFloat b = context.valueOf(B.get(i));
             molecule = molecule.add(a.multiply(b));
             sunA = sunA.add(a);
             sunB = sunB.add(a);
        }

        denominator = BigFloat.sqrt(sunA).multiply(BigFloat.sqrt(sunB));

        double result = molecule.divide(denominator).toDouble();

        return result;
    }

    public static CurrentUserSimilar getSimilar(UserBook currentUser, List<UserBook> compareUser){
        CurrentUserSimilar similar = new CurrentUserSimilar();
        similar.setUserId(currentUser.getId());
        List<UserSimilar> similarList = new ArrayList<>();
        for (UserBook compare: compareUser){
            UserSimilar userSimilar = new UserSimilar();
            userSimilar.setUserId(compare.getId());
            double cos= calcSimilarityBetweenUsers(currentUser,compare);
            userSimilar.setSimilar(cos);
            similarList.add(userSimilar);
        }
        similar.setSimilars(similarList);

        return sort(similar);
    }

    public static CurrentUserSimilar sort(CurrentUserSimilar similar){
        int length = similar.getSimilars().size();
        // 排序算法
        for (int i = 0; i < length - 1; i++){
            for (int j = i+1; j < length -1; j++){
                UserSimilar a = similar.getSimilars().get(i);
                UserSimilar b = similar.getSimilars().get(j);
                if (a.getSimilar()< (b.getSimilar())){
                    similar.getSimilars().set(i,b);
                    similar.getSimilars().set(j,a);
                }
            }
        }

        List<UserSimilar> similarRs = new ArrayList<>();
        similarRs.add(similar.getSimilars().get(0));
        for (UserSimilar s: similar.getSimilars()){
            int size = similarRs.size();
            if (size == 5) break;
            if (!similarRs.get(size - 1).getUserId().equals(s.getUserId())){
                similarRs.add(s);
            }
        }

        similar.setSimilars(similarRs);

        return similar;
    }
}
