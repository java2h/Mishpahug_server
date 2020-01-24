package application.configurations.dbloader;

//TODO: profile?
public enum MPHEntity {
	USER {
		public String dataFile() {
			return "data_user.csv";
		}
	},
	ARDUINO {
		public String dataFile() {
			return "data_arduino.csv";
		}
	},
	DEVICE {
		public String dataFile() {
			return "data_device.csv";
		}
	},
	SENSOR {
		public String dataFile() {
			return "data_sensor.csv";
		}
	},
	OPTIONS {
		public String dataFile() {
			return "data_options.csv";
		}
	},
	LOGS {
		public String dataFile() {
			return "data_blank.csv";
		}
	};

	public abstract String dataFile();
}