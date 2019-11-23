package application.configurations.dbloader;

//TODO: profile?
public enum MPHEntity {
	USER {
		public String dataFile() {
			return "data_user.csv";
		}
	},
	LOGS {
		public String dataFile() {
			return "data_blank.csv";
		}
	};

	public abstract String dataFile();
}