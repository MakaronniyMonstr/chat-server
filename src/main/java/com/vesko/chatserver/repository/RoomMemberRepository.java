package com.vesko.chatserver.repository;

import com.vesko.chatserver.entity.RoomMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RoomMemberRepository extends JpaRepository<RoomMember, Long>, JpaSpecificationExecutor<RoomMember> {
}
