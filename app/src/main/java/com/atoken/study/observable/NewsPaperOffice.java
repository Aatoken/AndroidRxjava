package com.atoken.study.observable;

import java.util.ArrayList;
import java.util.List;

/**
 * Author Aatoken
 * Date 2019/7/3 9:49
 * Description 报纸社(具体的主题)
 */
public class NewsPaperOffice implements ISubject {

    private int time = 1;
    //定报人的集合
    private List<IObserver> mList = new ArrayList<>();

    @Override
    public void registerObserver(IObserver iObserver) {
        if (iObserver != null) {
            mList.add(iObserver);

        }
    }

    @Override
    public void removeObserver(IObserver iObserver) {
        mList.remove(iObserver);
    }

    @Override
    public void notifyObserver() {
        for (int i = 0; i < mList.size(); i++) {
            mList.get(i).update("第"+time +"次发的新闻");
        }
        time ++ ;
    }
}
