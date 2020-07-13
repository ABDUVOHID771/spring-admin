package com.example.springloan.service;

import com.example.springloan.dao.domain.LoanType;
import com.example.springloan.dao.repository.LoanTypeRepository;
import com.google.common.base.Strings;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoanTypeService extends BaseService<LoanTypeRepository, LoanType> {

    public LoanTypeService(LoanTypeRepository repository) {
        super(repository);
    }

    @Override
    public LoanType update(LoanType updating) throws NotFoundException {

        LoanType loanType = getRepository().findById(updating.getId()).orElseThrow(() -> new NotFoundException("Not found !"));

        if (!Strings.isNullOrEmpty(updating.getName())) {
            loanType.setName(updating.getName());
        }
        if (updating.getMaxAmount() != null) {
            loanType.setMaxAmount(updating.getMaxAmount());
        }
        if (updating.getMinAmount() != null) {
            loanType.setMinAmount(updating.getMinAmount());
        }
        if (updating.getMaxMonth() != 0) {
            loanType.setMaxMonth(updating.getMaxMonth());
        }
        if (updating.getMinMonth() != 0) {
            loanType.setMinMonth(updating.getMinMonth());
        }
        if (updating.getCardPayment() != 0) {
            loanType.setCardPayment(updating.getCardPayment());
        }
        if (updating.getInsurance() != null) {
            loanType.setInsurance(updating.getInsurance());
        }
        if (updating.getInsurancePrice() != null) {
            loanType.setInsurancePrice(updating.getInsurancePrice());
        }

        loanType.setStatus(updating.isStatus());
        loanType.setAnnuitet(updating.isAnnuitet());
        loanType.setDifferential(updating.isDifferential());

        LoanType updated = getRepository().save(loanType);

        return updated;

    }

}
