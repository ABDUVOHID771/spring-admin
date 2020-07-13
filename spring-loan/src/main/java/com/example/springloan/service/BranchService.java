package com.example.springloan.service;

import com.example.springloan.dao.domain.Branch;
import com.example.springloan.dao.repository.BranchRepository;
import com.google.common.base.Strings;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BranchService extends BaseService<BranchRepository,Branch> {

    public BranchService(BranchRepository repository) {
        super(repository);
    }

    @Override
    public Branch update(Branch updating) throws NotFoundException {

        Branch branch = getRepository().findById(updating.getId()).orElseThrow(() -> new NotFoundException("Not found !"));

        if (!Strings.isNullOrEmpty(updating.getBranchName())) {
            branch.setBranchName(updating.getBranchName());
        }
        if (!Strings.isNullOrEmpty(updating.getPhone())) {
            branch.setPhone(updating.getPhone());
        }
        if (updating.getAddress() != null) {
            branch.setAddress(updating.getAddress());
        }
        if (updating.getLat() != null) {
            branch.setLat(updating.getLat());
        }
        if (updating.getLonG() != null) {
            branch.setLonG(updating.getLonG());
        }
        branch.setStatus(updating.isStatus());

        Branch updated = getRepository().save(branch);

        return updated;
    }
}
