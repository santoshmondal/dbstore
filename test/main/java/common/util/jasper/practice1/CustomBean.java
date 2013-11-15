package common.util.jasper.practice1;

public class CustomBean {

	private final String city;
	private final Integer id;
	private final String name;
	private final String street;

	public CustomBean(String pcity, Integer pid, String pname, String pstreet) {
		city = pcity;
		id = pid;
		name = pname;
		street = pstreet;
	}

	public CustomBean getMe() {
		return this;
	}

	public String getCity() {
		return city;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getStreet() {
		return street;
	}

}
