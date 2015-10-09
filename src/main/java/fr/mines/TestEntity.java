package fr.mines;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NOM_DE_TABLE")
public class TestEntity {
	@Id
	@Column(name = "ID")
	private Long id;
}
