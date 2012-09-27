package jphone;

public class Constants {

	public static int RSL_IE_CHAN_NR = 0x01;
	public static int RSL_IE_LINK_IDENT = 0x02;
	public static int RSL_IE_ACT_TYPE = 0x03;
	public static int RSL_IE_BS_POWER = 0x04;
	public static int RSL_IE_CHAN_IDENT = 0x05;
	public static int RSL_IE_CHAN_MODE = 0x06;
	public static int RSL_IE_ENCR_INFO = 0x07;
	public static int RSL_IE_FRAME_NUMBER = 0x08;
	public static int RSL_IE_HANDO_REF = 0x09;
	public static int RSL_IE_L1_INFO = 0x0a;
	public static int RSL_IE_L3_INFO = 0x0b;
	public static int RSL_IE_MS_IDENTITY = 0x0c;
	public static int RSL_IE_MS_POWER = 0x0d;
	public static int RSL_IE_PAGING_GROUP = 0x0e;
	public static int RSL_IE_PAGING_LOAD = 0x0f;
	public static int RSL_IE_PYHS_CONTEXT = 0x10;
	public static int RSL_IE_ACCESS_DELAY = 0x11;
	public static int RSL_IE_RACH_LOAD = 0x12;
	public static int RSL_IE_REQ_REFERENCE = 0x13;
	public static int RSL_IE_RELEASE_MODE = 0x14;
	public static int RSL_IE_RESOURCE_INFO = 0x15;

	/* Radio Link Layer Management */
	public static int RSL_MT_DATA_REQ = 0x01;
	public static int RSL_MT_DATA_IND = 0x02;
	public static int RSL_MT_ERROR_IND = 0x03;
	public static int RSL_MT_EST_REQ = 0x04;
	public static int RSL_MT_EST_CONF = 0x05;
	public static int RSL_MT_EST_IND = 0x06;
	public static int RSL_MT_REL_REQ = 0x07;
	public static int RSL_MT_REL_CONF = 0x08;
	public static int RSL_MT_REL_IND = 0x09;
	public static int RSL_MT_UNIT_DATA_REQ = 0x0a;
	public static int RSL_MT_UNIT_DATA_IND = 0x0b;/* 0x0b */
	public static int RSL_MT_SUSP_REQ = 0x0c; /* non-standard elements */
	public static int RSL_MT_SUSP_CONF = 0x0d;
	public static int RSL_MT_RES_REQ = 0x0e;
	public static int RSL_MT_RECON_REQ = 0x0f; /* 0x0f */
	/* Common Channel Management / TRX Management */
	public static int RSL_MT_BCCH_INFO = 0x11;
	public static int RSL_MT_CCCH_LOAD_IND = 0x12;
	public static int RSL_MT_CHAN_RQD = 0x13;
	public static int RSL_MT_DELETE_IND = 0x14;
	public static int RSL_MT_PAGING_CMD = 0x15;
	public static int RSL_MT_IMMEDIATE_ASSIGN_CMD = 0x16;
	public static int RSL_MT_SMS_BC_REQ = 0x17;
	public static int RSL_MT_CHAN_CONF = 0x18; /* non-standard element */

	public static int FREQ_TYPE_SERV = 0x01; /* frequency of the serving cell */
	public static int FREQ_TYPE_HOPP = 0x02; /*
											 * frequency used for channel
											 * hopping
											 */
	public static int FREQ_TYPE_NCELL = 0x1c; /* frequency of the neighbor cell */
	public static int FREQ_TYPE_NCELL_2 = 0x04; /* sub channel of SI 2 */
	public static int FREQ_TYPE_NCELL_2bis = 0x08; /* sub channel of SI 2bis */
	public static int FREQ_TYPE_NCELL_2ter = 0x10; /* sub channel of SI 2ter */
	public static int FREQ_TYPE_REP = 0xe0; /* frequency to be reported */
	public static int FREQ_TYPE_REP_5 = 0x20; /* sub channel of SI 5 */
	public static int FREQ_TYPE_REP_5bis = 0x40; /* sub channel of SI 5bis */
	public static int FREQ_TYPE_REP_5ter = 0x80; /* sub channel of SI 5ter */

	public static int ABIS_RSL_MDISC_RLL = 0x02;
	public static int ABIS_RSL_MDISC_DED_CHAN = 0x08;
	public static int ABIS_RSL_MDISC_COM_CHAN = 0x0c;
	public static int ABIS_RSL_MDISC_TRX = 0x10;
	public static int ABIS_RSL_MDISC_LOC = 0x20;
	public static int ABIS_RSL_MDISC_IPACCESS = 0x7e;
	public static int ABIS_RSL_MDISC_TRANSP = 0x01;

	public static int SAP_GSM_PH = 0;
	public static int SAP_GSM_DL = 1;
	public static int SAP_GSM_MDL = 2;

	public static int RSL_CHAN_NR_MASK = 0xf8;
	public static int RSL_CHAN_Bm_ACCHs = 0x08;
	public static int RSL_CHAN_Lm_ACCHs = 0x10; /* .. 0x18 */
	public static int RSL_CHAN_SDCCH4_ACCH = 0x20; /* .. 0x38 */
	public static int RSL_CHAN_SDCCH8_ACCH = 0x40; /* ...0x78 */
	public static int RSL_CHAN_BCCH = 0x80;
	public static int RSL_CHAN_RACH = 0x88;
	public static int RSL_CHAN_PCH_AGCH = 0x90;

	public static int GSM48_MT_RR_SYSINFO_8 = 0x18;
	public static int GSM48_MT_RR_SYSINFO_1 = 0x19;
	public static int GSM48_MT_RR_SYSINFO_2 = 0x1a;
	public static int GSM48_MT_RR_SYSINFO_3 = 0x1b;
	public static int GSM48_MT_RR_SYSINFO_4 = 0x1c;
	public static int GSM48_MT_RR_SYSINFO_5 = 0x1d;
	public static int GSM48_MT_RR_SYSINFO_6 = 0x1e;
	public static int GSM48_MT_RR_SYSINFO_7 = 0x1f;

	public static int GSM48_MT_RR_SYSINFO_2bis = 0x02;
	public static int GSM48_MT_RR_SYSINFO_2ter = 0x03;
	public static int GSM48_MT_RR_SYSINFO_5bis = 0x05;
	public static int GSM48_MT_RR_SYSINFO_5ter = 0x06;
	public static int GSM48_MT_RR_SYSINFO_9 = 0x04;
	
	public static int GSM48_MT_RR_SYSINFO_13 = 0x00;
	//public static int GSM48_MT_RR_SYSINFO_GPRS_7 = 0x07;
	
	public static int GSM48_MT_RR_SYSINFO_16 = 0x3d;
	public static int GSM48_MT_RR_SYSINFO_17 = 0x3e;

	public static int GSM48_MT_RR_PAG_REQ_1 = 0x21;
	public static int GSM48_MT_RR_PAG_REQ_2 = 0x22;
	public static int GSM48_MT_RR_PAG_REQ_3 = 0x24;

	public static int GSM48_MT_RR_IMM_ASS = 0x3f;
	public static int GSM48_MT_RR_IMM_ASS_EXT = 0x39;
	public static int GSM48_MT_RR_IMM_ASS_REJ = 0x3a;

	public static int GSM48_IE_CBCH_CHAN_DESC = 0x64;
	public static int GSM48_IE_CBCH_MOB_AL = 0x72;

	public static int gsm48_max_retrans[] = { 1, 2, 4, 7 };
	public static int gsm48_tx_integer[] = { 3, 4, 5, 6, 7, 8, 9, 10, 11, 12,
			14, 16, 20, 25, 32, 50 };

	public static int PRIM_PH_DATA = 0;
	public static int PRIM_PH_RACH = 1;
	public static int PRIM_PH_CONN = 2;
	public static int PRIM_PH_EMPTY_FRAME = 3;
	public static int PRIM_PH_RTS = 4;

	public static int LAPDM_MODE_MS = 0;
	public static int LAPDM_MODE_BTS = 1;

	public static int LAPDm_FMT_A = 0;
	public static int LAPDm_FMT_B = 1;
	public static int LAPDm_FMT_Bbis = 2;
	public static int LAPDm_FMT_Bter = 3;
	public static int LAPDm_FMT_B4 = 4;

	public static int N201_AB_SACCH = 18;
	public static int N201_AB_SDCCH = 20;
	public static int N201_AB_FACCH = 20;
	public static int N201_Bbis = 23;
	public static int N201_Bter_SACCH = 21;
	public static int N201_Bter_SDCCH = 23;
	public static int N201_Bter_FACCH = 23;
	public static int N201_B4 = 19;

	public static int S_GLOBAL_SHUTDOWN = 0;

	public static int S_L1CTL_FBSB_ERR = 0;
	public static int S_L1CTL_FBSB_RESP = 1;
	public static int S_L1CTL_RESET = 2;
	public static int S_L1CTL_PM_RES = 3;
	public static int S_L1CTL_PM_DONE = 4;
	public static int S_L1CTL_CCCH_MODE_CONF = 5;
	public static int S_L1CTL_TCH_MODE_CONF = 6;
	public static int S_L1CTL_LOSS_IND = 7;
	public static int S_L1CTL_NEIGH_PM_IND = 8;

	public static int SS_L1CTL = 0;
	public static int SS_GLOBAL = 1;

	public static int PRIM_OP_REQUEST = 0;
	public static int PRIM_OP_RESPONSE = 1;
	public static int PRIM_OP_INDICATION = 2;
	public static int PRIM_OP_CONFIRM = 3;

	public static int CCCH_MODE_NONE = 0;
	public static int CCCH_MODE_NON_COMBINED = 1;
	public static int CCCH_MODE_COMBINED = 2;

	public static int NEIGH_MODE_NONE = 0;
	public static int NEIGH_MODE_PM = 1;
	public static int NEIGH_MODE_SB = 2;

	public static int L1CTL_RES_T_BOOT = 0;
	public static int L1CTL_RES_T_FULL = 1;
	public static int L1CTL_RES_T_SCHED = 2;

	public static int TRAFFIC_DATA_LEN = 40;

	public static int L1CTL_F_DONE = 0x01;

	public static int _L1CTL_NONE = 0;
	public static int L1CTL_FBSB_REQ = 1;
	public static int L1CTL_FBSB_CONF = 2;
	public static int L1CTL_DATA_IND = 3;
	public static int L1CTL_RACH_REQ = 4;
	public static int L1CTL_DM_EST_REQ = 5;
	public static int L1CTL_DATA_REQ = 6;
	public static int L1CTL_RESET_IND = 7;
	public static int L1CTL_PM_REQ = 8; /* power measurement */
	public static int L1CTL_PM_CONF = 9; /* power measurement */
	public static int L1CTL_ECHO_REQ = 10;
	public static int L1CTL_ECHO_CONF = 11;
	public static int L1CTL_RACH_CONF = 12;
	public static int L1CTL_RESET_REQ = 13;
	public static int L1CTL_RESET_CONF = 14;
	public static int L1CTL_DATA_CONF = 15;
	public static int L1CTL_CCCH_MODE_REQ = 16;
	public static int L1CTL_CCCH_MODE_CONF = 17;
	public static int L1CTL_DM_REL_REQ = 18;
	public static int L1CTL_PARAM_REQ = 19;
	public static int L1CTL_DM_FREQ_REQ = 20;
	public static int L1CTL_CRYPTO_REQ = 21;
	public static int L1CTL_SIM_REQ = 22;
	public static int L1CTL_SIM_CONF = 23;
	public static int L1CTL_TCH_MODE_REQ = 24;
	public static int L1CTL_TCH_MODE_CONF = 25;
	public static int L1CTL_NEIGH_PM_REQ = 26;
	public static int L1CTL_NEIGH_PM_IND = 27;
	public static int L1CTL_TRAFFIC_REQ = 28;
	public static int L1CTL_TRAFFIC_CONF = 29;
	public static int L1CTL_TRAFFIC_IND = 30;

	public static int L1CTL_FBSB_F_FB0 = (1 << 0);
	public static int L1CTL_FBSB_F_FB1 = (1 << 1);
	public static int L1CTL_FBSB_F_SB = (1 << 2);
	public static int L1CTL_FBSB_F_FB01SB = (L1CTL_FBSB_F_FB0
			| L1CTL_FBSB_F_FB1 | L1CTL_FBSB_F_SB);

	public static int HDLC_FLAG = 0x7E;
	public static int HDLC_ESCAPE = 0x7D;
	public static int HDLC_C_UI = 0x03;

	public static String osmocomHeader = "5 3 ";

	public static int TESTING = 0;
	public static int WAITING_PROMPT1 = 1;
	public static int WAITING_PROMPT2 = 2;
	public static int WAITING_ACK = 3;
	public static int FORWARDING = 4;

	public static int[] phone_prompt1 = { 0x1b, 0xf6, 0x02, 0x00, 0x41, 0x01,
			0x40 };
	public static int[] dnload_cmd = { 0x1b, 0xf6, 0x02, 0x00, 0x52, 0x01, 0x53 };
	public static int[] phone_prompt2 = { 0x1b, 0xf6, 0x02, 0x00, 0x41, 0x02,
			0x43 };
	public static int[] data_hdr_c123 = { 0xee, 0x4c, 0x9f, 0x63 };
	public static int[] phone_ack = { 0x1b, 0xf6, 0x02, 0x00, 0x41, 0x03, 0x42 };

	public static int xor_init = 0x02;

	public static String reset = "126 5 3 13 125 32 125 32 125 32 1 125 32 125 32 125 32 126";
	public static String resetConf = "126 5 3 14 125 32 125 32 125 32 1 125 32 125 32 125 32 126";

	public static int SERIAL = 0;
	public static int RAM = 1;
	public static int OSMOCOM = 2;
	public static int LAYER1 = 3;
	public static int LAPDM = 4;

	public static int RAM_PERCENT = 0;
	public static int OSMOCOM_RADIO = 0;

	public static int MOBILE_IDLE = 0;
	public static int MOBILE_SCANNING = 1;
	public static int MOBILE_TUNING = 2;
}
