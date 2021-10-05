package com.vesko.chatserver.configuration;

import com.vesko.chatserver.entity.ERoomRole;
import com.vesko.chatserver.entity.Room;
import com.vesko.chatserver.entity.RoomMember;
import com.vesko.chatserver.entity.User;
import com.vesko.chatserver.repository.RoomMemberRepository;
import com.vesko.chatserver.repository.TokenBoxRepository;
import com.vesko.chatserver.repository.UserRepository;
import com.vesko.chatserver.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class Test implements CommandLineRunner {
    private final UserRepository userRepository;
    private final TokenBoxRepository tokenBoxRepository;
    private final RoomMemberRepository roomMemberRepository;
    private final ITokenBoxService tokenBoxService;
    private final IUserService userService;
    private final IRoomService roomService;
    private final IRoomMemberService roomMemberService;
    private final IRoomHelper roomHelper;

    @Autowired
    public Test(UserRepository userRepository,
                TokenBoxRepository tokenBoxRepository,
                RoomMemberRepository roomMemberRepository,
                ITokenBoxService tokenBoxService,
                IUserService userService,
                IRoomService roomService, IRoomMemberService roomMemberService, IRoomHelper roomHelper) {
        this.userRepository = userRepository;
        this.tokenBoxRepository = tokenBoxRepository;
        this.roomMemberRepository = roomMemberRepository;
        this.tokenBoxService = tokenBoxService;
        this.userService = userService;
        this.roomService = roomService;
        this.roomMemberService = roomMemberService;
        this.roomHelper = roomHelper;
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        User user = userService.findUserByUsername("admin");
        System.out.println(user.getRoomMembers());
        System.out.println(user.getRoomMembers().stream().findAny());
    }
}
