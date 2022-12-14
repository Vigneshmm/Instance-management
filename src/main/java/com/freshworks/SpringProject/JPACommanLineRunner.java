package com.freshworks.SpringProject;

import com.freshworks.SpringProject.team.Team;
import com.freshworks.SpringProject.team.TeamJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class JPACommanLineRunner implements CommandLineRunner {

    @Autowired
    private TeamJPA teamJPA;

    @Override
    public void run(String... args) throws Exception {
//        teamJPA.save(
//                new Team("Brute Force")
//        );
//        teamJPA.save(
//                new Team("CAC")
//        );
//        teamJPA.save(
//                new Team("Synergy")
//        );
//        System.out.println(teamJPA.findById(1l));
//        System.out.println(teamJPA.findById(2l));
//        System.out.println(teamJPA.findById(3l));
//        teamJPA.deleteById(3l);
//        System.out.println(teamJPA.findById(3l));
    }
}
