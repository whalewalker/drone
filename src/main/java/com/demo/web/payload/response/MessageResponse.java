package com.demo.web.payload.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class MessageResponse {
	private final String result;
	private final String message;
	private final LocalDateTime timestamp;
}
