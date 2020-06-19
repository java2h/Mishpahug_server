package application.configurations.dbloader;

//TODO: profile?
public enum MPHEntity {
	USER {
		public String dataFile() {
			return "data_user.csv";
		}
	},
	SAL {
		public String dataFile() {
			return "data_sal.csv";
		}
	},
	SAMPLE {
		public String dataFile() {
			return "data_sample.csv";
		}
	},
	ITEM {
		public String dataFile() {
			return "data_item.csv";
		}
	},
	ISHUR {
		public String dataFile() {
			return "data_ishur.csv";
		}
	},
	LOGS {
		public String dataFile() {
			return "data_blank.csv";
		}
	};

	public abstract String dataFile();
}