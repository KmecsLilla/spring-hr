package hu.webuni.hr.lilla.config;

import org.springframework.stereotype.Component;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "hr")
@Component
public class HrConfigurationProperties {
	
	private EmployeeAugment employeeAugment = new EmployeeAugment();
	
	public EmployeeAugment getEmployeeAugment() {
		return employeeAugment;
	}

	public void setAugment(EmployeeAugment employeeAugment) {
		this.employeeAugment = employeeAugment;
	}

	public static class EmployeeAugment {
		private Default def = new Default();
		private Smart smart1 = new Smart();
		private Smart smart2 = new Smart();
		private Smart smart3 = new Smart();
		
		public Default getDef() {
			return def;
		}
		
		public void setDef(Default def) {
			this.def = def;
		}
		
		public Smart getSmart1() {
			return smart1;
		}
		
		public void setSmart1(Smart smart1) {
			this.smart1 = smart1;
		}
		
		public Smart getSmart2() {
			return smart2;
		}
		
		public void setSmart2(Smart smart2) {
			this.smart2 = smart2;
		}
		
		public Smart getSmart3() {
			return smart3;
		}
		
		public void setSmart3(Smart smart3) {
			this.smart3 = smart3;
		}
	
	}
	
	public static class Default {
		private int percent;

		public int getPercent() {
			return percent;
		}

		public void setPercent(int percent) {
			this.percent = percent;
		}
	}
	
	public static class Smart {
		private int percent;
		private int limit;
		
		public int getPercent() {
			return percent;
		}
		
		public void setPercent(int percent) {
			this.percent = percent;
		}
		
		public int getLimit() {
			return limit;
		}
		
		public void setLimit(int limit) {
			this.limit = limit;
		}		
	}	
}


//hr.employeeaugment.def.percent = 5 
//hr.employeeaugment.smart.percenthigh = 10
//hr.employeeaugment.smart.limithigh = 120
//hr.employeeaugment.smart.percentmid = 5
//hr.employeeaugment.smart.limitmid = 60
//hr.employeeaugment.smart.percentlow = 2
//hr.employeeaugment.smart.limitlow = 30
