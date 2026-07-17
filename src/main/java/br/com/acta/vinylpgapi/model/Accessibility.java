package br.com.acta.vinylpgapi.model;

import br.com.acta.vinylpgapi.model.base.NameDescriptionBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "accessibility")
public class Accessibility extends NameDescriptionBase {
}