package com.sun.webblog.service;

import com.sun.webblog.dao.QuestionandanswerDao;
import com.sun.webblog.entity.Questionandanswer;
import com.sun.webblog.util.Similarity;
import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author ken
 * @date 2019-5-2  10:10
 * @description
 */
@Service
public class QuestionAndAnswerService {

    @Resource
    QuestionandanswerDao questionandanswerDao;


    public String getAnwser(String question)
    {
        List<String> tem=new ArrayList<>(5);
        Result parse = ToAnalysis.parse(question.trim());
        List<Term> terms = parse.getTerms();
        for (int i=0;i<terms.size()&&i<5;i++)
        {
            Term term=terms.get(i);
            String natureStr = term.getNatureStr();
            if (natureStr.equals("n")||natureStr.equals("vn"))
            {
                if (!tem.contains(term.getName())) {
                    tem.add(term.getName());
                }
            }
        }
        if(tem.size()==0)
        {
            return "暂时未收录改问题。";
        }

          Object[] objects = tem.toArray();
        List<Questionandanswer> answers = questionandanswerDao.getAnswer(objects);
        if(answers.size()==0)
        {
            return "暂时未收录改问题。";
        }
        double maxSimilar=0;
        Questionandanswer resultAnser=null;
        for (Questionandanswer answer : answers) {
            String question1 = answer.getQuestion();
            Similarity similarity=new Similarity(question,question1);
            double sim = similarity.sim();
            if(maxSimilar<sim)
            {
                maxSimilar=sim;
                resultAnser=answer;
            }
        }
        return  resultAnser.getAnswer();
    }


}
