package com.den.model;

import com.den.model.ext.Game;
import com.den.model.ext.User;
import com.den.model.id.UserGameID;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name = "game_user")
@AssociationOverrides({
		@AssociationOverride(name = "pk.game",
			joinColumns = @JoinColumn(name = "id_game", referencedColumnName = "id")),
		@AssociationOverride(name = "pk.user",
			joinColumns = @JoinColumn(name = "id_user", referencedColumnName = "id")) })
public class UserGame implements java.io.Serializable {
 
	private UserGameID pk = new UserGameID();

	@NotBlank
	@Length(min = 2, max = 30)
	@Column(name="account")
	private String account;

	@Column(name="info")
	private String info;

	public UserGame() {
	}
 
	@EmbeddedId
	public UserGameID getPk() {
		return pk;
	}
 
	public void setPk(UserGameID pk) {
		this.pk = pk;
	}

	@Transient
	public Game getGame() {
		return getPk().getGame();
	}

	public void setGame(Game game) {
		getPk().setGame(game);
	}


	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Transient
	@JsonIgnore
	public User getUser() {
		return getPk().getUser();
	}

	public void setUser(User user) {
		getPk().setUser(user);
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
 
		UserGame that = (UserGame) o;

		return !(getPk() != null ? !getPk().equals(that.getPk())
				: that.getPk() != null);

	}
 
	public int hashCode() {
		return (getPk() != null ? getPk().hashCode() : 0);
	}
}