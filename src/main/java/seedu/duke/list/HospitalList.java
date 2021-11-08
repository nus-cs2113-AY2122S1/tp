package seedu.duke.list;

import java.util.ArrayList;

import seedu.duke.hospital.Hospital;

public class HospitalList {

    /**
     * Constants for hospital variables: name and number.
     */
    public static final String CHANGI_GENERAL_HOSPITAL = "changi general hospital";
    public static final Integer CHANGI_GENERAL_HOSPITAL_NUMBER = 67888833;
    public static final String KHOO_TECK_PUAT_HOSPITAL = "khoo teck puat hospital";
    public static final Integer KHOO_TECK_PUAT_HOSPITAL_NUMBER = 65558000;
    public static final String NATIONAL_UNIVERSITY_HOSPITAL = "national university hospital";
    public static final Integer NATIONAL_UNIVERSITY_HOSPITAL_NUMBER = 67795555;
    public static final String NG_TENG_FONG_GENERAL_HOSPITAL = "ng teng feng general hospital";
    public static final Integer NG_TENG_FONG_GENERAL_HOSPITAL_NUMBER = 67162000;
    public static final String SINGAPORE_GENERAL_HOSPITAL = "singapore general hospital";
    public static final Integer SINGAPORE_GENERAL_HOSPITAL_NUMBER = 62223322;
    public static final String TAN_TOCK_SENG_HOSPITAL = "tan tock seng hospital";
    public static final Integer TAN_TOCK_SENG_HOSPITAL_NUMBER = 62566011;

    /**
     * Constants for Changi General Hospital doctor variables: name and number.
     */
    public static final String MA_XUEGANG = "ma xuegang";
    public static final Integer MA_XUEGANG_NUMBER = 97587759;
    public static final String XIE_FANG = "xie fang";
    public static final Integer XIE_FANG_NUMBER = 99577818;
    public static final String PETER_GANG = "peter gang";
    public static final Integer PETER_GANG_NUMBER = 94501442;
    public static final String JON_YI = "jon yi";
    public static final Integer JON_YI_NUMBER = 83027553;
    public static final String HAN_GUANTING = "han guanting";
    public static final Integer HAN_GUANTING_NUMBER = 95674975;

    /**
     * Constants for Khoo Teck Puat Hospital doctor variables: name and number.
     */
    public static final String ADRIAN_VERGIL = "adrian vergil";
    public static final Integer ADRIAN_VERGIL_NUMBER = 88129931;
    public static final String JOLINE_DEAN = "joline dean";
    public static final Integer JOLINE_DEAN_NUMBER = 98672657;
    public static final String BRITNEY_SOH = "britney soh";
    public static final Integer BRITNEY_SOH_NUMBER = 91982575;
    public static final String SHUI_LORIN = "shui lorin";
    public static final Integer SHUI_LORIN_NUMBER = 99046617;
    public static final String ADITI_NITYA = "aditi nitya";
    public static final Integer ADITI_NITYA_NUMBER = 91208702;

    /**
     * Constants for National University Hospital doctor variables: name and number.
     */
    public static final String DWAIN_WONG = "dwain wong";
    public static final Integer DWAIN_WONG_NUMBER = 98105561;
    public static final String LIM_QINGCHEN = "lim qingchen";
    public static final Integer LIM_QINGCHEN_NUMBER = 96737937;
    public static final String LING_LI_SE = "ling li se";
    public static final Integer LING_LI_SE_NUMBER = 83892911;
    public static final String JUAN_CHELSIE = "juan chelsie";
    public static final Integer JUAN_CHELSIE_NUMBER = 97915260;
    public static final String LORIE_PANG = "lorie pang";
    public static final Integer LORIE_PANG_NUMBER = 88235081;

    /**
     * Constants for Ng Teng Fong Hospital doctor variables: name and number.
     */
    public static final String SUN_GENGXIN = "sun gengxin";
    public static final Integer SUN_GENGXIN_NUMBER = 98770442;
    public static final String DAI_JIA = "dai jia";
    public static final Integer DAI_JIA_NUMBER = 84357329;
    public static final String XAVIER_YUAN = "xavier yuan";
    public static final Integer XAVIER_YUAN_NUMBER = 80270436;
    public static final String ZENG_ZEMIN = "zeng zemin";
    public static final Integer ZENG_ZEMIN_NUMBER = 82609095;
    public static final String FU_JING = "fu jing";
    public static final Integer FU_JING_NUMBER = 99444710;

    /**
     * Constants for Singapore General Hospital doctor variables: name and number.
     */
    public static final String NILOFER_SIDDHI = "nilofer siddhi";
    public static final Integer NILOFER_SIDDHI_NUMBER = 92834168;
    public static final String TARAN_NIRMALA = "taran nirmala";
    public static final Integer TARAN_NIRMALA_NUMBER = 90877889;
    public static final String PAXTON_JERROD = "paxton jerrod";
    public static final Integer PAXTON_JERROD_NUMBER = 94424161;
    public static final String PIERS_COLEMAN = "piers coleman";
    public static final Integer PIERS_COLEMAN_NUMBER = 99350450;
    public static final String TAN_CAILIN = "tan cailin";
    public static final Integer TAN_CAILIN_NUMBER = 99897593;

    /**
     * Constants for Tan Tock Seng Hospital doctor variables: name and number.
     */
    public static final String MARALYN_HARSHA = "maralyn harsha";
    public static final Integer MARALYN_HARSHA_NUMBER = 99026440;
    public static final String STACE_FANG = "stace fang";
    public static final Integer STACE_FANG_NUMBER = 91074243;
    public static final String CHANTELLE_JIA = "chantelle jia";
    public static final Integer CHANTELLE_JIA_NUMBER = 80463166;
    public static final String YASMIN_ANG = "yasmin ang";
    public static final Integer YASMIN_ANG_NUMBER = 81338150;
    public static final String WOLF_EMMET = "wolf emmet";
    public static final Integer WOLF_EMMET_NUMBER = 89490406;

    /**
     * Array list of all hospitals.
     */
    private final ArrayList<Hospital> hospitalArrayList = new ArrayList<Hospital>();

    public HospitalList() {
    }

    /**
     * Initialises hospitalArrayList array based on constants.
     */
    public void initHospitals() {
        hospitalArrayList.add(initializeChangiGeneralHospital());
        hospitalArrayList.add(initializeKhooTeckPuatHospital());
        hospitalArrayList.add(initializeNationalUniversityHospital());
        hospitalArrayList.add(initializeNgTengFongHospital());
        hospitalArrayList.add(initializeSingaporeGeneralHospital());
        hospitalArrayList.add(initializeTanTockSengHospital());
    }

    /**
     * Prints all hospital names.
     */
    public void printHospitalNames() {
        int counter = 1;
        System.out.println("List of hospitals are shown below:");
        for (Hospital hospital : hospitalArrayList) {
            System.out.format("% 3d.", counter);
            System.out.println(hospital.getHospitalName());
            counter++;
        }
    }

    /**
     * Prints length of hospitalArrayList.
     */
    public Integer getLength() {
        return hospitalArrayList.size();
    }

    /**
     * Returns the hospital in hospitalArrayList given the index.
     *
     * @param index Index of hospital.
     * @return Hospital instance of given index is returned.
     */
    public Hospital getHospital(int index) {
        return hospitalArrayList.get(index);
    }

    /**
     * Initialises Changi General Hospital variables based on constants.
     */
    private Hospital initializeChangiGeneralHospital() {
        Hospital changiGeneralHospital = new Hospital(CHANGI_GENERAL_HOSPITAL, CHANGI_GENERAL_HOSPITAL_NUMBER);
        changiGeneralHospital.addDoctor(MA_XUEGANG, MA_XUEGANG_NUMBER);
        changiGeneralHospital.addDoctor(XIE_FANG, XIE_FANG_NUMBER);
        changiGeneralHospital.addDoctor(PETER_GANG, PETER_GANG_NUMBER);
        changiGeneralHospital.addDoctor(JON_YI, JON_YI_NUMBER);
        changiGeneralHospital.addDoctor(HAN_GUANTING, HAN_GUANTING_NUMBER);
        return changiGeneralHospital;
    }

    /**
     * Initialises Khoo Teck Puat Hospital variables based on constants.
     */
    private Hospital initializeKhooTeckPuatHospital() {
        Hospital khooTeckPuatHospital = new Hospital(KHOO_TECK_PUAT_HOSPITAL, KHOO_TECK_PUAT_HOSPITAL_NUMBER);
        khooTeckPuatHospital.addDoctor(ADRIAN_VERGIL, ADRIAN_VERGIL_NUMBER);
        khooTeckPuatHospital.addDoctor(JOLINE_DEAN, JOLINE_DEAN_NUMBER);
        khooTeckPuatHospital.addDoctor(SHUI_LORIN, SHUI_LORIN_NUMBER);
        khooTeckPuatHospital.addDoctor(BRITNEY_SOH, BRITNEY_SOH_NUMBER);
        khooTeckPuatHospital.addDoctor(ADITI_NITYA, ADITI_NITYA_NUMBER);
        return khooTeckPuatHospital;
    }

    /**
     * Initialises National University Hospital variables based on constants.
     */
    private Hospital initializeNationalUniversityHospital() {
        Hospital nationalUniversityHospital = new Hospital(NATIONAL_UNIVERSITY_HOSPITAL,
                NATIONAL_UNIVERSITY_HOSPITAL_NUMBER);
        nationalUniversityHospital.addDoctor(DWAIN_WONG, DWAIN_WONG_NUMBER);
        nationalUniversityHospital.addDoctor(LIM_QINGCHEN, LIM_QINGCHEN_NUMBER);
        nationalUniversityHospital.addDoctor(LING_LI_SE, LING_LI_SE_NUMBER);
        nationalUniversityHospital.addDoctor(JUAN_CHELSIE, JUAN_CHELSIE_NUMBER);
        nationalUniversityHospital.addDoctor(LORIE_PANG, LORIE_PANG_NUMBER);
        return nationalUniversityHospital;
    }

    /**
     * Initialises Ng Teng Fong Hospital variables based on constants.
     */
    private Hospital initializeNgTengFongHospital() {
        Hospital ngTengFongHospital = new Hospital(NG_TENG_FONG_GENERAL_HOSPITAL, NG_TENG_FONG_GENERAL_HOSPITAL_NUMBER);
        ngTengFongHospital.addDoctor(SUN_GENGXIN, SUN_GENGXIN_NUMBER);
        ngTengFongHospital.addDoctor(DAI_JIA, DAI_JIA_NUMBER);
        ngTengFongHospital.addDoctor(XAVIER_YUAN, XAVIER_YUAN_NUMBER);
        ngTengFongHospital.addDoctor(ZENG_ZEMIN, ZENG_ZEMIN_NUMBER);
        ngTengFongHospital.addDoctor(FU_JING, FU_JING_NUMBER);
        return ngTengFongHospital;

    }

    /**
     * Initialises Singapore General Hospital variables based on constants.
     */
    private Hospital initializeSingaporeGeneralHospital() {
        Hospital singaporeGeneralHospital = new Hospital(SINGAPORE_GENERAL_HOSPITAL, SINGAPORE_GENERAL_HOSPITAL_NUMBER);
        singaporeGeneralHospital.addDoctor(NILOFER_SIDDHI, NILOFER_SIDDHI_NUMBER);
        singaporeGeneralHospital.addDoctor(TARAN_NIRMALA, TARAN_NIRMALA_NUMBER);
        singaporeGeneralHospital.addDoctor(PAXTON_JERROD, PAXTON_JERROD_NUMBER);
        singaporeGeneralHospital.addDoctor(PIERS_COLEMAN, PIERS_COLEMAN_NUMBER);
        singaporeGeneralHospital.addDoctor(TAN_CAILIN, TAN_CAILIN_NUMBER);
        return singaporeGeneralHospital;
    }

    /**
     * Initialises Tan Tock Seng Hospital variables based on constants.
     */
    private Hospital initializeTanTockSengHospital() {
        Hospital tanTockSengHospital = new Hospital(TAN_TOCK_SENG_HOSPITAL, TAN_TOCK_SENG_HOSPITAL_NUMBER);
        tanTockSengHospital.addDoctor(MARALYN_HARSHA, MARALYN_HARSHA_NUMBER);
        tanTockSengHospital.addDoctor(STACE_FANG, STACE_FANG_NUMBER);
        tanTockSengHospital.addDoctor(CHANTELLE_JIA, CHANTELLE_JIA_NUMBER);
        tanTockSengHospital.addDoctor(YASMIN_ANG, YASMIN_ANG_NUMBER);
        tanTockSengHospital.addDoctor(WOLF_EMMET, WOLF_EMMET_NUMBER);
        return tanTockSengHospital;
    }
}
