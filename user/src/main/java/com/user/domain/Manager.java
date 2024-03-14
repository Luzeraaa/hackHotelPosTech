package com.user.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity()
@Getter
@Setter
@Table(name = "tb_manager")
public class Manager extends Persona {

  private DocumentType documentType = DocumentType.CNPJ;
  private String documentNumber;

}
