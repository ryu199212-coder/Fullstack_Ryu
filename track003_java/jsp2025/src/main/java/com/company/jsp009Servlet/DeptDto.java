package com.company.jsp009Servlet;

public class DeptDto {
	private int deptno;
	private String dname;
	private String loc;
	
	public DeptDto() { super();  }
	public DeptDto(int deptno, String dname, String loc) { super(); this.deptno = deptno; this.dname = dname; this.loc = loc; }
	
	@Override public String toString() { return "DEPTDto [deptno=" + deptno + ", dname=" + dname + ", loc=" + loc + "]"; }
	
	
	
	public int getDeptno() { return deptno; } public void setDeptno(int deptno) { this.deptno = deptno; }
	public String getDname() { return dname; } public void setDname(String dname) { this.dname = dname; }
	public String getLoc() { return loc; } public void setLoc(String loc) { this.loc = loc; }
	
	
}
