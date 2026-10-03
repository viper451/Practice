package com.example.Splitwise;

import com.example.Splitwise.entity.User;

import java.util.HashMap;
import java.util.Map;

public class Balance {

    public static void updateBalance(User paidBy, HashMap<Integer, Integer> shares){
        for (Map.Entry<Integer, Integer> itr : shares.entrySet()) {
            int debtorId = itr.getKey();
            int value = itr.getValue();

            // skip if payer is also listed (they owe themselves)
            if (debtorId == paidBy.getId()) continue;

            // update payer's credit map: how much each debtor owes the payer
            Integer prev = paidBy.getCredit().get(debtorId);
            paidBy.getCredit().put(debtorId, (prev == null ? 0 : prev) + value);

            // update debtor's own map: record that debtor owes the payer
            User debtor = User.findUserById(debtorId);
            if (debtor != null) {
                Integer p = debtor.getOwn().get(paidBy.getId());
                debtor.getOwn().put(paidBy.getId(), (p == null ? 0 : p) + value);
            }
        }


    }
}
