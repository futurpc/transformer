package com.samagra.transformer.odk.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "xmessage")
public class GupshupMessageEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column(nullable = false, name = "phone_no")
  private String phoneNo;
  
  @Column(nullable = false, name = "message")
  private String message;

  @Column(name = "is_last_message")
  private boolean isLastResponse;
}
