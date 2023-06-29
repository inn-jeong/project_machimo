package com.example.project_machimo.alert.service;

import com.example.project_machimo.alert.dao.AlertDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlertServiceImpl implements AlertService {

    private final AlertDAO alertDAO;

    @Autowired
    public AlertServiceImpl(AlertDAO alertDAO) {
        this.alertDAO = alertDAO;
    }




    @Override
    public Integer alertResult(int uId) {
        return alertDAO.countResult(uId);
    }
}
