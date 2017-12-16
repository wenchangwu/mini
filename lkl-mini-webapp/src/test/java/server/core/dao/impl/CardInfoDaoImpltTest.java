package server.core.dao.impl;

import com.lakala.mini.LKLBaseApplicationTests;
import com.lakala.mini.server.core.dao.ICardInfoDAO;
import com.lakala.mini.server.core.domain.CardInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CardInfoDaoImpltTest extends LKLBaseApplicationTests {

    @Autowired
    private ICardInfoDAO cardInfoDAO;

    @Test
    public void testGetByCardNo(){
        CardInfo cardInfo=cardInfoDAO.getByCardNo("622909326218670018");
        System.out.println(cardInfo);
    }
}
