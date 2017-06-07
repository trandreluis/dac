package br.edu.ifpb.mt.daca.entities.enumeration;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.edu.ifpb.mt.daca.util.Util;

@Entity
@Table(name = "TB_CREDIT_CARD")
public class CreditCard {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(nullable = false, unique = true)
	private String numberCC;

	@Temporal(TemporalType.DATE)
	private Date expiryDate;

	private Integer controlNumber;

	@Enumerated(EnumType.STRING)
	private CreditCardType creditCardType;

	public CreditCard() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getNumberCC() {
		return numberCC;
	}

	
	public void setNumberCC(String numberCC) {
		this.numberCC = numberCC;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Integer getControlNumber() {
		return controlNumber;
	}

	public void setControlNumber(Integer controlNumber) {
		this.controlNumber = controlNumber;
	}

	public CreditCardType getCreditCardType() {
		return creditCardType;
	}

	public void setCreditCardType(CreditCardType creditCardType) {
		this.creditCardType = creditCardType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((controlNumber == null) ? 0 : controlNumber.hashCode());
		result = prime * result + ((creditCardType == null) ? 0 : creditCardType.hashCode());
		result = prime * result + ((expiryDate == null) ? 0 : Util.removeTime(expiryDate).hashCode());
		result = prime * result + ((numberCC == null) ? 0 : numberCC.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreditCard other = (CreditCard) obj;
		if (controlNumber == null) {
			if (other.controlNumber != null)
				return false;
		} else if (!controlNumber.equals(other.controlNumber))
			return false;
		if (creditCardType != other.creditCardType)
			return false;
		if (expiryDate == null) {
			if (other.expiryDate != null)
				return false;
		} else if (!Util.removeTime(expiryDate).equals(Util.removeTime(other.expiryDate)))
			return false;
		if (numberCC == null) {
			if (other.numberCC != null)
				return false;
		} else if (!numberCC.equals(other.numberCC))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CreditCard [id=" + id + ", number=" + numberCC + ", expiryDate=" + expiryDate + ", controlNumber="
				+ controlNumber + ", creditCardType=" + creditCardType + "]";
	}
	
	public long getRandomCC() {
		long min = 1000000000000000l;
		long max = 9999999999999999l;
		return (long) Math.floor(Math.random() * (max - min + 1)) + min;
	}
}
