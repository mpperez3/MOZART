package es.uvigo.ei.sing.MOZART;
/**
 * Java deployment code of Neural Networks Model==========================================================================
 * Before running the Java deployment code please read the following.
 * <p>
 * STATISTICA variable names will be exported as-is into the Java deployment script;
 * please verify the resulting script to ensure that the variable names follow the Java
 * naming conventions and modify the names if necessary.
 * <p>
 * ==========================================================================
 **/

/**==========================================================================
 Before running the Java deployment code please read the following.

 STATISTICA variable names will be exported as-is into the Java deployment script;
 please verify the resulting script to ensure that the variable names follow the Java
 naming conventions and modify the names if necessary.

 ==========================================================================**/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Predict {

    public static String __Spreadsh_MLP_9_30_2(double[] ContInputs) {

        //"Input Variable" comment is added besides Input(Response) variables.


        int Cont_idx = 0;

        double _J_EMB__ = ContInputs[Cont_idx++]; //Input Variable

        double _WTPT_5__ = ContInputs[Cont_idx++]; //Input Variable

        double _SC_3__ = ContInputs[Cont_idx++]; //Input Variable

        double __J_EMB___ = ContInputs[Cont_idx++]; //Input Variable

        double __SC_3___ = ContInputs[Cont_idx++]; //Input Variable

        double __WTPT_5___ = ContInputs[Cont_idx++]; //Input Variable

        double _DJ_EMB__ = ContInputs[Cont_idx++]; //Input Variable

        double _DSC_3__ = ContInputs[Cont_idx++]; //Input Variable

        double _DWTPT_5__ = ContInputs[Cont_idx++]; //Input Variable

        String __statist_PredCat = "";

        String[] __statist_DCats = new String[2];

        __statist_DCats[0] = "-1";

        __statist_DCats[1] = "1";


        double __statist_ConfLevel = 3.0E-300;


        double[] __statist_max_input = new double[9];

        __statist_max_input[0] = 1.44184652000000e-001;

        __statist_max_input[1] = 3.45174765400000e+001;

        __statist_max_input[2] = 8.03619798600000e+000;

        __statist_max_input[3] = 6.53460720000000e-002;

        __statist_max_input[4] = 3.77898341200000e+000;

        __statist_max_input[5] = 1.01675671100000e+001;

        __statist_max_input[6] = 1.09991506000000e-001;

        __statist_max_input[7] = 6.12049078200000e+000;

        __statist_max_input[8] = 2.60916391800000e+001;


        double[] __statist_min_input = new double[9];

        __statist_min_input[0] = 5.11487800000000e-003;

        __statist_min_input[1] = 0.00000000000000e+000;

        __statist_min_input[2] = 2.04124145000000e-001;

        __statist_min_input[3] = 7.74358800000000e-003;

        __statist_min_input[4] = 1.84924951900000e+000;

        __statist_min_input[5] = 2.52022519100000e+000;

        __statist_min_input[6] = -5.42530250000000e-002;

        __statist_min_input[7] = -3.21622458000000e+000;

        __statist_min_input[8] = -1.01675671100000e+001;


        double[][] __statist_i_h_wts = new double[30][9];


        __statist_i_h_wts[0][0] = -8.66687137790160e+001;

        __statist_i_h_wts[0][1] = -4.89087431102877e+001;

        __statist_i_h_wts[0][2] = 3.53226361643894e+000;

        __statist_i_h_wts[0][3] = -3.98265249817178e+001;

        __statist_i_h_wts[0][4] = -2.13437742765768e+001;

        __statist_i_h_wts[0][5] = 5.14853223021968e+000;

        __statist_i_h_wts[0][6] = -5.07127353466258e+001;

        __statist_i_h_wts[0][7] = 1.20382590205855e+001;

        __statist_i_h_wts[0][8] = -4.18582405424353e+001;


        __statist_i_h_wts[1][0] = -4.93229440492325e+001;

        __statist_i_h_wts[1][1] = -6.29030036995543e+000;

        __statist_i_h_wts[1][2] = -1.33836397197314e+001;

        __statist_i_h_wts[1][3] = 4.68270747517717e+001;

        __statist_i_h_wts[1][4] = -4.35002718951336e+001;

        __statist_i_h_wts[1][5] = -1.86991341633744e+001;

        __statist_i_h_wts[1][6] = -5.11854934714194e+001;

        __statist_i_h_wts[1][7] = 1.49558175053966e+000;

        __statist_i_h_wts[1][8] = 2.62667357265650e+000;


        __statist_i_h_wts[2][0] = 2.03641500413274e+000;

        __statist_i_h_wts[2][1] = 1.30404507879786e+000;

        __statist_i_h_wts[2][2] = -6.08749170049250e+001;

        __statist_i_h_wts[2][3] = -1.74875799503809e+001;

        __statist_i_h_wts[2][4] = -3.40381177595266e+001;

        __statist_i_h_wts[2][5] = 1.31256663131675e+002;

        __statist_i_h_wts[2][6] = -4.15530313128227e+000;

        __statist_i_h_wts[2][7] = -5.04444728355593e+001;

        __statist_i_h_wts[2][8] = -3.45342124792808e+001;


        __statist_i_h_wts[3][0] = 4.60118837665254e-001;

        __statist_i_h_wts[3][1] = 7.41032760847337e-001;

        __statist_i_h_wts[3][2] = -2.86483082181426e+000;

        __statist_i_h_wts[3][3] = 5.50523377752121e+000;

        __statist_i_h_wts[3][4] = -1.67493452436149e+000;

        __statist_i_h_wts[3][5] = 8.33105907405357e-001;

        __statist_i_h_wts[3][6] = -1.24042088424933e+000;

        __statist_i_h_wts[3][7] = -1.85630803830182e+000;

        __statist_i_h_wts[3][8] = 7.75469733479677e-001;


        __statist_i_h_wts[4][0] = 3.78426106779280e+001;

        __statist_i_h_wts[4][1] = 5.97005354495590e+001;

        __statist_i_h_wts[4][2] = 1.39800244669809e+001;

        __statist_i_h_wts[4][3] = -3.21632444311725e+001;

        __statist_i_h_wts[4][4] = -6.56334773877439e+000;

        __statist_i_h_wts[4][5] = 8.25453462570388e+001;

        __statist_i_h_wts[4][6] = 3.31297446055212e+001;

        __statist_i_h_wts[4][7] = 7.60253822737849e+000;

        __statist_i_h_wts[4][8] = 3.26554439232242e+001;


        __statist_i_h_wts[5][0] = -8.65869220423946e-001;

        __statist_i_h_wts[5][1] = -1.43150605060498e+000;

        __statist_i_h_wts[5][2] = 3.72931956009843e+000;

        __statist_i_h_wts[5][3] = -7.49803173589380e+000;

        __statist_i_h_wts[5][4] = 2.74350163223960e+000;

        __statist_i_h_wts[5][5] = -9.73344734948615e-001;

        __statist_i_h_wts[5][6] = 1.48328025972140e+000;

        __statist_i_h_wts[5][7] = 2.45214085849706e+000;

        __statist_i_h_wts[5][8] = -1.35788514053989e+000;


        __statist_i_h_wts[6][0] = -1.29588097296626e+001;

        __statist_i_h_wts[6][1] = -4.27871530569681e+000;

        __statist_i_h_wts[6][2] = 5.78589804017767e+000;

        __statist_i_h_wts[6][3] = 1.51361064408775e+001;

        __statist_i_h_wts[6][4] = -1.77731451796262e+001;

        __statist_i_h_wts[6][5] = -1.74902947562868e+001;

        __statist_i_h_wts[6][6] = -1.49031031993874e+001;

        __statist_i_h_wts[6][7] = 9.29432109801851e+000;

        __statist_i_h_wts[6][8] = 6.02466094249673e-001;


        __statist_i_h_wts[7][0] = 5.34796531209725e+000;

        __statist_i_h_wts[7][1] = 1.05104709049527e+001;

        __statist_i_h_wts[7][2] = -2.85742163110993e+001;

        __statist_i_h_wts[7][3] = 2.49171706692807e+001;

        __statist_i_h_wts[7][4] = -3.96214503824183e+001;

        __statist_i_h_wts[7][5] = 1.95204709478388e+001;

        __statist_i_h_wts[7][6] = -6.10115397293613e+000;

        __statist_i_h_wts[7][7] = -1.68030591491000e+001;

        __statist_i_h_wts[7][8] = 4.59626414031693e+000;


        __statist_i_h_wts[8][0] = 1.37228114578355e+001;

        __statist_i_h_wts[8][1] = 3.98472582641454e+001;

        __statist_i_h_wts[8][2] = 1.35830735211548e+001;

        __statist_i_h_wts[8][3] = 2.10195066844629e+000;

        __statist_i_h_wts[8][4] = 1.55037595600078e+001;

        __statist_i_h_wts[8][5] = 2.02698252102821e+001;

        __statist_i_h_wts[8][6] = 2.29829283209941e-001;

        __statist_i_h_wts[8][7] = 2.51508568697730e+000;

        __statist_i_h_wts[8][8] = 2.65321020029616e+001;


        __statist_i_h_wts[9][0] = 9.36598794490433e+000;

        __statist_i_h_wts[9][1] = -6.87625369536633e+000;

        __statist_i_h_wts[9][2] = -4.60242273429127e+000;

        __statist_i_h_wts[9][3] = 1.49109129561456e+001;

        __statist_i_h_wts[9][4] = -6.48673473807514e+001;

        __statist_i_h_wts[9][5] = -3.27202135725843e+001;

        __statist_i_h_wts[9][6] = 6.03015035886410e+000;

        __statist_i_h_wts[9][7] = 1.13868287863976e+001;

        __statist_i_h_wts[9][8] = 2.65454578734470e+000;


        __statist_i_h_wts[10][0] = -7.94493100600839e+000;

        __statist_i_h_wts[10][1] = -1.94709757127547e+001;

        __statist_i_h_wts[10][2] = 1.18978046356942e+001;

        __statist_i_h_wts[10][3] = -7.31736047018057e+000;

        __statist_i_h_wts[10][4] = -1.70050316652404e+001;

        __statist_i_h_wts[10][5] = -3.37002568605798e+001;

        __statist_i_h_wts[10][6] = 4.17898556297725e+000;

        __statist_i_h_wts[10][7] = 1.79669069425145e+001;

        __statist_i_h_wts[10][8] = -5.81849616545664e+000;


        __statist_i_h_wts[11][0] = 2.63226229157892e+000;

        __statist_i_h_wts[11][1] = 4.05377725677358e+000;

        __statist_i_h_wts[11][2] = 2.14174763830105e+000;

        __statist_i_h_wts[11][3] = -2.21592060973262e+001;

        __statist_i_h_wts[11][4] = 3.39631025006357e+001;

        __statist_i_h_wts[11][5] = -6.71652572764295e+000;

        __statist_i_h_wts[11][6] = 1.22603347050304e+001;

        __statist_i_h_wts[11][7] = -4.03821530068146e+000;

        __statist_i_h_wts[11][8] = 6.76964728726117e+000;


        __statist_i_h_wts[12][0] = -2.85929328332429e+001;

        __statist_i_h_wts[12][1] = -3.08490706809323e+001;

        __statist_i_h_wts[12][2] = 1.34089424605746e+001;

        __statist_i_h_wts[12][3] = -6.55736179276188e+001;

        __statist_i_h_wts[12][4] = 1.11290732481020e+002;

        __statist_i_h_wts[12][5] = -5.16230791910452e+001;

        __statist_i_h_wts[12][6] = 1.13792040712935e+001;

        __statist_i_h_wts[12][7] = -5.12356573409602e+000;

        __statist_i_h_wts[12][8] = -1.01617851831671e+001;


        __statist_i_h_wts[13][0] = 3.78098119626283e+001;

        __statist_i_h_wts[13][1] = -1.82571010151071e+001;

        __statist_i_h_wts[13][2] = 1.32037080469800e+001;

        __statist_i_h_wts[13][3] = 7.70485374878508e+001;

        __statist_i_h_wts[13][4] = 4.76885559071701e+001;

        __statist_i_h_wts[13][5] = 1.88752918665643e+001;

        __statist_i_h_wts[13][6] = -1.45858668617688e+001;

        __statist_i_h_wts[13][7] = -9.21134321771578e+000;

        __statist_i_h_wts[13][8] = -3.45019294933605e+001;


        __statist_i_h_wts[14][0] = 1.19461549550460e+002;

        __statist_i_h_wts[14][1] = 2.60483378795136e+001;

        __statist_i_h_wts[14][2] = -1.12728647519310e+001;

        __statist_i_h_wts[14][3] = -2.10539724053284e-001;

        __statist_i_h_wts[14][4] = -3.35673316030016e+001;

        __statist_i_h_wts[14][5] = 6.97009858220497e+001;

        __statist_i_h_wts[14][6] = 8.50726182099138e+001;

        __statist_i_h_wts[14][7] = -1.10682950183238e+001;

        __statist_i_h_wts[14][8] = -8.15283466533441e-001;


        __statist_i_h_wts[15][0] = 6.92544429161480e+000;

        __statist_i_h_wts[15][1] = 5.64217581756993e+000;

        __statist_i_h_wts[15][2] = -4.96417252082629e+000;

        __statist_i_h_wts[15][3] = -8.34142831231561e-001;

        __statist_i_h_wts[15][4] = 2.04322211376884e+000;

        __statist_i_h_wts[15][5] = 1.40018744363672e+001;

        __statist_i_h_wts[15][6] = 2.39040280836950e+000;

        __statist_i_h_wts[15][7] = -6.53024641523731e+000;

        __statist_i_h_wts[15][8] = -1.50917039222867e-001;


        __statist_i_h_wts[16][0] = 5.21515830167168e+000;

        __statist_i_h_wts[16][1] = 2.15643105971036e+001;

        __statist_i_h_wts[16][2] = -4.54639942060142e+001;

        __statist_i_h_wts[16][3] = 8.09312028100594e+001;

        __statist_i_h_wts[16][4] = -1.54727823696682e+001;

        __statist_i_h_wts[16][5] = -2.37272097783453e+001;

        __statist_i_h_wts[16][6] = -1.63369554395987e+001;

        __statist_i_h_wts[16][7] = -3.09131474969948e+001;

        __statist_i_h_wts[16][8] = 3.05861055738438e+001;


        __statist_i_h_wts[17][0] = -6.75494737728870e+001;

        __statist_i_h_wts[17][1] = 5.49538289402466e+001;

        __statist_i_h_wts[17][2] = -7.48155512721271e+001;

        __statist_i_h_wts[17][3] = 3.15057305078769e+001;

        __statist_i_h_wts[17][4] = -3.47640060120966e+001;

        __statist_i_h_wts[17][5] = 1.06114212669344e+002;

        __statist_i_h_wts[17][6] = -7.29435246101905e+001;

        __statist_i_h_wts[17][7] = -5.81381298510265e+001;

        __statist_i_h_wts[17][8] = 2.67915925184149e+001;


        __statist_i_h_wts[18][0] = -5.47264245593631e+001;

        __statist_i_h_wts[18][1] = -6.15494295884677e+001;

        __statist_i_h_wts[18][2] = -3.50889386751575e+000;

        __statist_i_h_wts[18][3] = -1.06788933312042e+000;

        __statist_i_h_wts[18][4] = -2.28219973304805e+001;

        __statist_i_h_wts[18][5] = -6.58208374568959e+000;

        __statist_i_h_wts[18][6] = -3.87752503760026e+001;

        __statist_i_h_wts[18][7] = 5.60796278923274e+000;

        __statist_i_h_wts[18][8] = -5.23023613892130e+001;


        __statist_i_h_wts[19][0] = 3.25435621668694e+001;

        __statist_i_h_wts[19][1] = -3.86350208608075e+001;

        __statist_i_h_wts[19][2] = -6.51622566889349e+000;

        __statist_i_h_wts[19][3] = -3.51669487580758e+001;

        __statist_i_h_wts[19][4] = -2.45839151109807e+001;

        __statist_i_h_wts[19][5] = 6.81580777175665e+001;

        __statist_i_h_wts[19][6] = 3.04234433388812e+001;

        __statist_i_h_wts[19][7] = -5.46291429348693e+000;

        __statist_i_h_wts[19][8] = -5.74996521152937e+001;


        __statist_i_h_wts[20][0] = -1.52013783861011e+000;

        __statist_i_h_wts[20][1] = 2.67209771903060e+001;

        __statist_i_h_wts[20][2] = -1.26530087928723e+001;

        __statist_i_h_wts[20][3] = -1.06130819350276e+001;

        __statist_i_h_wts[20][4] = -1.01839003648903e+001;

        __statist_i_h_wts[20][5] = -5.40819984892977e+000;

        __statist_i_h_wts[20][6] = 3.01976026833793e+000;

        __statist_i_h_wts[20][7] = -8.20958185473857e+000;

        __statist_i_h_wts[20][8] = 2.68202448170463e+001;


        __statist_i_h_wts[21][0] = -1.84041355271198e+001;

        __statist_i_h_wts[21][1] = 2.06390836267242e+001;

        __statist_i_h_wts[21][2] = -1.21725330395519e+001;

        __statist_i_h_wts[21][3] = -3.06142587471560e+001;

        __statist_i_h_wts[21][4] = -4.27265675351376e+000;

        __statist_i_h_wts[21][5] = 1.74960864843128e+001;

        __statist_i_h_wts[21][6] = -7.81242551028411e+000;

        __statist_i_h_wts[21][7] = -1.09326809961367e+001;

        __statist_i_h_wts[21][8] = 1.39884722582543e+001;


        __statist_i_h_wts[22][0] = -1.54530197713177e+000;

        __statist_i_h_wts[22][1] = -6.26611483903420e+001;

        __statist_i_h_wts[22][2] = 3.28743806018318e+001;

        __statist_i_h_wts[22][3] = 4.46784875774087e+001;

        __statist_i_h_wts[22][4] = -1.33373223360144e+002;

        __statist_i_h_wts[22][5] = -3.28505459639249e+001;

        __statist_i_h_wts[22][6] = -2.97285723667493e+001;

        __statist_i_h_wts[22][7] = 4.82144673768538e+001;

        __statist_i_h_wts[22][8] = -6.12945287770900e+001;


        __statist_i_h_wts[23][0] = 1.51212629475385e+001;

        __statist_i_h_wts[23][1] = -6.70061089621593e+000;

        __statist_i_h_wts[23][2] = -4.28593040715784e+000;

        __statist_i_h_wts[23][3] = 9.52113896718663e+001;

        __statist_i_h_wts[23][4] = -4.31556298174747e+001;

        __statist_i_h_wts[23][5] = -2.41361487286470e+001;

        __statist_i_h_wts[23][6] = -1.21961732398224e+001;

        __statist_i_h_wts[23][7] = 9.78715884476059e+000;

        __statist_i_h_wts[23][8] = 4.35430238711382e+000;


        __statist_i_h_wts[24][0] = -6.11452239948069e-001;

        __statist_i_h_wts[24][1] = -9.35920326487575e+000;

        __statist_i_h_wts[24][2] = -2.89970196160227e+000;

        __statist_i_h_wts[24][3] = -1.87917968272026e+000;

        __statist_i_h_wts[24][4] = 6.39238245457710e+000;

        __statist_i_h_wts[24][5] = 7.63412836590957e+000;

        __statist_i_h_wts[24][6] = -5.47105066212946e-001;

        __statist_i_h_wts[24][7] = -4.12640263882806e+000;

        __statist_i_h_wts[24][8] = -1.10045582818707e+001;


        __statist_i_h_wts[25][0] = 1.17462331904468e+000;

        __statist_i_h_wts[25][1] = 1.07008572345744e+001;

        __statist_i_h_wts[25][2] = 5.20121337395663e+000;

        __statist_i_h_wts[25][3] = 3.19101260186229e+000;

        __statist_i_h_wts[25][4] = -4.38185431842424e+000;

        __statist_i_h_wts[25][5] = -5.60983442112705e+000;

        __statist_i_h_wts[25][6] = -5.71341363397463e-001;

        __statist_i_h_wts[25][7] = 4.94748736772191e+000;

        __statist_i_h_wts[25][8] = 1.11122314080311e+001;


        __statist_i_h_wts[26][0] = 1.34576475340391e+001;

        __statist_i_h_wts[26][1] = -4.34047261329870e+001;

        __statist_i_h_wts[26][2] = -1.47354950855195e+001;

        __statist_i_h_wts[26][3] = -2.33343605391239e+001;

        __statist_i_h_wts[26][4] = 1.57624523038078e+001;

        __statist_i_h_wts[26][5] = -1.57176412594656e+001;

        __statist_i_h_wts[26][6] = 2.20262577490679e+001;

        __statist_i_h_wts[26][7] = -1.43013945919061e+001;

        __statist_i_h_wts[26][8] = -3.63554781408868e+001;


        __statist_i_h_wts[27][0] = 1.99031687053240e+000;

        __statist_i_h_wts[27][1] = 3.89516620464627e+001;

        __statist_i_h_wts[27][2] = -3.52430589268860e+001;

        __statist_i_h_wts[27][3] = -8.97116774372819e+001;

        __statist_i_h_wts[27][4] = 4.78788362859610e+001;

        __statist_i_h_wts[27][5] = -3.05384217196346e+001;

        __statist_i_h_wts[27][6] = -4.56786854792014e-001;

        __statist_i_h_wts[27][7] = -5.73931191119189e+001;

        __statist_i_h_wts[27][8] = 2.09730828571085e+001;


        __statist_i_h_wts[28][0] = 4.91840867752079e+001;

        __statist_i_h_wts[28][1] = 1.13272388001495e+000;

        __statist_i_h_wts[28][2] = 1.79780339110820e+001;

        __statist_i_h_wts[28][3] = 1.82956066443272e+001;

        __statist_i_h_wts[28][4] = 8.06263712250456e+001;

        __statist_i_h_wts[28][5] = -3.15057088505094e+001;

        __statist_i_h_wts[28][6] = 2.56229109592941e+001;

        __statist_i_h_wts[28][7] = -6.72286547671178e+000;

        __statist_i_h_wts[28][8] = 1.25113157229249e+000;


        __statist_i_h_wts[29][0] = -4.99326468786286e+000;

        __statist_i_h_wts[29][1] = -9.02088145245604e+000;

        __statist_i_h_wts[29][2] = 2.21009415277661e+001;

        __statist_i_h_wts[29][3] = -2.09628998915914e+001;

        __statist_i_h_wts[29][4] = 3.37429647980536e+001;

        __statist_i_h_wts[29][5] = -1.84515450083328e+001;

        __statist_i_h_wts[29][6] = 5.40080304417423e+000;

        __statist_i_h_wts[29][7] = 1.27896161165969e+001;

        __statist_i_h_wts[29][8] = -3.18827515584509e+000;


        double[][] __statist_h_o_wts = new double[2][30];


        __statist_h_o_wts[0][0] = 6.31417757014952e+001;

        __statist_h_o_wts[0][1] = 5.82966953677990e-001;

        __statist_h_o_wts[0][2] = -5.05381776285604e-001;

        __statist_h_o_wts[0][3] = 8.85801362123632e+000;

        __statist_h_o_wts[0][4] = 1.40403532525648e+001;

        __statist_h_o_wts[0][5] = 9.24287950190246e+000;

        __statist_h_o_wts[0][6] = 1.01658068590436e+001;

        __statist_h_o_wts[0][7] = 8.26958551353258e+000;

        __statist_h_o_wts[0][8] = 6.40339717118347e-001;

        __statist_h_o_wts[0][9] = 5.70399702670776e+000;

        __statist_h_o_wts[0][10] = 8.42305931642188e-001;

        __statist_h_o_wts[0][11] = 1.62867180610584e+001;

        __statist_h_o_wts[0][12] = -4.84679391646768e-001;

        __statist_h_o_wts[0][13] = -1.05080382230157e+000;

        __statist_h_o_wts[0][14] = 9.41340752014315e-001;

        __statist_h_o_wts[0][15] = 7.06509168468167e-001;

        __statist_h_o_wts[0][16] = 1.21056768693351e+000;

        __statist_h_o_wts[0][17] = 2.35541464970792e-001;

        __statist_h_o_wts[0][18] = 7.91431184022184e+000;

        __statist_h_o_wts[0][19] = 8.39268642273374e-001;

        __statist_h_o_wts[0][20] = -5.86458743259146e-001;

        __statist_h_o_wts[0][21] = 7.43474314420493e-001;

        __statist_h_o_wts[0][22] = -5.18371033540751e+001;

        __statist_h_o_wts[0][23] = 9.59159909741820e-001;

        __statist_h_o_wts[0][24] = 2.51075377071838e+000;

        __statist_h_o_wts[0][25] = 1.98406433409839e+000;

        __statist_h_o_wts[0][26] = -1.13938561007114e+000;

        __statist_h_o_wts[0][27] = -3.22651415636058e+000;

        __statist_h_o_wts[0][28] = 4.22079218551420e-001;

        __statist_h_o_wts[0][29] = 9.30662190623215e+000;


        __statist_h_o_wts[1][0] = -6.31527314513956e+001;

        __statist_h_o_wts[1][1] = -5.92596744471845e-001;

        __statist_h_o_wts[1][2] = 4.84294430503107e-001;

        __statist_h_o_wts[1][3] = -8.85357706937486e+000;

        __statist_h_o_wts[1][4] = -1.40602221485826e+001;

        __statist_h_o_wts[1][5] = -9.24451674269542e+000;

        __statist_h_o_wts[1][6] = -1.01428568312625e+001;

        __statist_h_o_wts[1][7] = -8.29976438558483e+000;

        __statist_h_o_wts[1][8] = -6.16434306340293e-001;

        __statist_h_o_wts[1][9] = -5.71322078254038e+000;

        __statist_h_o_wts[1][10] = -8.33333108706069e-001;

        __statist_h_o_wts[1][11] = -1.62845978934634e+001;

        __statist_h_o_wts[1][12] = 5.10757571582522e-001;

        __statist_h_o_wts[1][13] = 1.04493765588705e+000;

        __statist_h_o_wts[1][14] = -9.54448317230466e-001;

        __statist_h_o_wts[1][15] = -6.81474240057351e-001;

        __statist_h_o_wts[1][16] = -1.22750405124403e+000;

        __statist_h_o_wts[1][17] = -3.19184778260560e-001;

        __statist_h_o_wts[1][18] = -7.95144005113605e+000;

        __statist_h_o_wts[1][19] = -8.43258451842613e-001;

        __statist_h_o_wts[1][20] = 5.97996715736851e-001;

        __statist_h_o_wts[1][21] = -7.46537883121014e-001;

        __statist_h_o_wts[1][22] = 5.18552416333488e+001;

        __statist_h_o_wts[1][23] = -9.44049761064146e-001;

        __statist_h_o_wts[1][24] = -2.53072298979122e+000;

        __statist_h_o_wts[1][25] = -1.91725666152055e+000;

        __statist_h_o_wts[1][26] = 1.18334826913422e+000;

        __statist_h_o_wts[1][27] = 3.22870564992051e+000;

        __statist_h_o_wts[1][28] = -4.02535554276886e-001;

        __statist_h_o_wts[1][29] = -9.35504851302094e+000;


        double[] __statist_hidden_bias = new double[30];

        __statist_hidden_bias[0] = 2.76932694430929e+001;

        __statist_hidden_bias[1] = 2.22605135964171e+001;

        __statist_hidden_bias[2] = -3.82227912350516e+001;

        __statist_hidden_bias[3] = 9.47500727475371e-001;

        __statist_hidden_bias[4] = -3.24363550143481e+001;

        __statist_hidden_bias[5] = -9.78239876570348e-001;

        __statist_hidden_bias[6] = 4.59487026424125e+000;

        __statist_hidden_bias[7] = -6.21914487703848e+000;

        __statist_hidden_bias[8] = -3.39046403665596e+001;

        __statist_hidden_bias[9] = 1.06956413568478e+001;

        __statist_hidden_bias[10] = 2.64618924120671e+001;

        __statist_hidden_bias[11] = 7.18066061354531e+000;

        __statist_hidden_bias[12] = 3.94222021215188e+001;

        __statist_hidden_bias[13] = -6.21037995768298e+001;

        __statist_hidden_bias[14] = -5.13948901688809e+001;

        __statist_hidden_bias[15] = -1.20086415010061e+001;

        __statist_hidden_bias[16] = 2.39597846628049e+001;

        __statist_hidden_bias[17] = -1.51360815526582e+001;

        __statist_hidden_bias[18] = 2.29805136185752e+001;

        __statist_hidden_bias[19] = -3.01809971923947e+001;

        __statist_hidden_bias[20] = 1.62729195797205e+000;

        __statist_hidden_bias[21] = -9.55669679794475e+000;

        __statist_hidden_bias[22] = -4.07072881119997e+001;

        __statist_hidden_bias[23] = 2.66356516491559e+001;

        __statist_hidden_bias[24] = -2.19849338048748e+000;

        __statist_hidden_bias[25] = -1.40321878205686e+000;

        __statist_hidden_bias[26] = 7.98701273178360e+000;

        __statist_hidden_bias[27] = -1.06664523336230e+002;

        __statist_hidden_bias[28] = -3.06896994617293e+001;

        __statist_hidden_bias[29] = 7.24519701770725e+000;


        double[] __statist_output_bias = new double[2];

        __statist_output_bias[0] = 1.42947348039354e-001;

        __statist_output_bias[1] = -1.20711570046246e-001;


        double[] __statist_inputs = new double[9];


        double[] __statist_hidden = new double[30];


        double[] __statist_outputs = new double[2];

        __statist_outputs[0] = -1.0e+307;

        __statist_outputs[1] = -1.0e+307;


        __statist_inputs[0] = _J_EMB__;

        __statist_inputs[1] = _WTPT_5__;

        __statist_inputs[2] = _SC_3__;

        __statist_inputs[3] = __J_EMB___;

        __statist_inputs[4] = __SC_3___;

        __statist_inputs[5] = __WTPT_5___;

        __statist_inputs[6] = _DJ_EMB__;

        __statist_inputs[7] = _DSC_3__;

        __statist_inputs[8] = _DWTPT_5__;


        double __statist_delta = 0;

        double __statist_maximum = 1;

        double __statist_minimum = 0;

        int __statist_ncont_inputs = 9;



        /*scale continuous inputs*/

        for (int __statist_i = 0; __statist_i < __statist_ncont_inputs; __statist_i++) {

            __statist_delta = (__statist_maximum - __statist_minimum) / (__statist_max_input[__statist_i] - __statist_min_input[__statist_i]);

            __statist_inputs[__statist_i] = __statist_minimum - __statist_delta * __statist_min_input[__statist_i] + __statist_delta * __statist_inputs[__statist_i];

        }


        int __statist_ninputs = 9;

        int __statist_nhidden = 30;



        /*Compute feed forward signals from Input layer to hidden layer*/

        for (int __statist_row = 0; __statist_row < __statist_nhidden; __statist_row++) {

            __statist_hidden[__statist_row] = 0.0;

            for (int __statist_col = 0; __statist_col < __statist_ninputs; __statist_col++) {

                __statist_hidden[__statist_row] = __statist_hidden[__statist_row] + (__statist_i_h_wts[__statist_row][__statist_col] * __statist_inputs[__statist_col]);

            }

            __statist_hidden[__statist_row] = __statist_hidden[__statist_row] + __statist_hidden_bias[__statist_row];

        }


        for (int __statist_row = 0; __statist_row < __statist_nhidden; __statist_row++) {

            if (__statist_hidden[__statist_row] > 100.0) {

                __statist_hidden[__statist_row] = 1.0;

            } else {

                if (__statist_hidden[__statist_row] < -100.0) {

                    __statist_hidden[__statist_row] = -1.0;

                } else {

                    __statist_hidden[__statist_row] = Math.tanh(__statist_hidden[__statist_row]);

                }

            }

        }


        int __statist_noutputs = 2;



        /*Compute feed forward signals from hidden layer to output layer*/

        for (int __statist_row2 = 0; __statist_row2 < __statist_noutputs; __statist_row2++) {

            __statist_outputs[__statist_row2] = 0.0;

            for (int __statist_col2 = 0; __statist_col2 < __statist_nhidden; __statist_col2++) {

                __statist_outputs[__statist_row2] = __statist_outputs[__statist_row2] + (__statist_h_o_wts[__statist_row2][__statist_col2] * __statist_hidden[__statist_col2]);

            }

            __statist_outputs[__statist_row2] = __statist_outputs[__statist_row2] + __statist_output_bias[__statist_row2];

        }


        double __statist_sum = 0.0;

        double __statist_maxIndex = 0;

        for (int __statist_jj = 0; __statist_jj < __statist_noutputs; __statist_jj++) {

            if (__statist_outputs[__statist_jj] > 200) {

                double __statist_max = __statist_outputs[1];

                __statist_maxIndex = 0;

                for (int __statist_ii = 0; __statist_ii < __statist_noutputs; __statist_ii++) {

                    if (__statist_outputs[__statist_ii] > __statist_max) {

                        __statist_max = __statist_outputs[__statist_ii];

                        __statist_maxIndex = __statist_ii;

                    }

                }


                for (int __statist_kk = 0; __statist_kk < __statist_noutputs; __statist_kk++) {

                    if (__statist_kk == __statist_maxIndex) {

                        __statist_outputs[__statist_jj] = 1.0;

                    } else {

                        __statist_outputs[__statist_kk] = 0.0;

                    }

                }

            } else {

                __statist_outputs[__statist_jj] = Math.exp(__statist_outputs[__statist_jj]);

                __statist_sum = __statist_sum + __statist_outputs[__statist_jj];

            }

        }

        for (int __statist_ll = 0; __statist_ll < __statist_noutputs; __statist_ll++) {

            if (__statist_sum != 0) {

                __statist_outputs[__statist_ll] = __statist_outputs[__statist_ll] / __statist_sum;

            }

        }


        int __statist_PredIndex = 1;

        for (int __statist_ii = 0; __statist_ii < __statist_noutputs; __statist_ii++) {

            if (__statist_ConfLevel < __statist_outputs[__statist_ii]) {

                __statist_ConfLevel = __statist_outputs[__statist_ii];

                __statist_PredIndex = __statist_ii;

            }

        }


        __statist_PredCat = __statist_DCats[__statist_PredIndex];


        System.out.println(" Predicted Category = " + __statist_PredCat);
        System.out.println(" Confidence Level = " + __statist_ConfLevel);

        return __statist_PredCat;

    }


    public static void main(String[] args) {

        List<Map<String, String>> records;
        List<String> headers;
        try (BufferedReader br = new BufferedReader(new FileReader("E:\\Documentos\\NetBeansProjects\\ChemicalSmile\\src\\main\\resources\\train.tsv"))) {
            headers = Arrays.asList(br.readLine().split("\t"));
            records =
                    br.lines().map(s -> s.split("\t"))
                            .map(t -> IntStream.range(0, t.length)
                                    .boxed()
                                    .collect(Collectors.toMap(i -> headers.get(i).trim(), i -> t[i].trim())))
                            .collect(Collectors.toList());
//            System.out.println(headers);

            AtomicInteger TP = new AtomicInteger();
            AtomicInteger FP = new AtomicInteger();

            records.stream().forEach(stringStringMap -> {
                int argID = 0;
                double[] ContInputs = new double[9];
                int contID = 0;


                String Comment3 = "J_EMB  Type - double (or) integer\n";

                ContInputs[contID++] = Double.parseDouble(stringStringMap.get("J_EMB"));

                String Comment4 = "WTPT-5  Type - double (or) integer\n";

                ContInputs[contID++] = Double.parseDouble(stringStringMap.get("WTPT-5"));

                String Comment5 = "SC-3  Type - double (or) integer\n";

                ContInputs[contID++] = Double.parseDouble(stringStringMap.get("SC-3"));

                String Comment6 = "&lt;J_EMB&gt;  Type - double (or) integer\n";

                ContInputs[contID++] = Double.parseDouble(stringStringMap.get("<J_EMB>"));

                String Comment7 = "&lt;SC-3&gt;  Type - double (or) integer\n";

                ContInputs[contID++] = Double.parseDouble(stringStringMap.get("<SC-3>"));

                String Comment8 = "&lt;WTPT-5&gt;  Type - double (or) integer\n";

                ContInputs[contID++] = Double.parseDouble(stringStringMap.get("<WTPT-5>"));

                String Comment9 = "DJ_EMB  Type - double (or) integer\n";

                ContInputs[contID++] = Double.parseDouble(stringStringMap.get("DJ_EMB"));

                String Comment10 = "DSC-3  Type - double (or) integer\n";

                ContInputs[contID++] = Double.parseDouble(stringStringMap.get("DSC-3"));

                String Comment11 = "DWTPT-5  Type - double (or) integer\n";

                ContInputs[contID++] = Double.parseDouble(stringStringMap.get("DWTPT-5"));

                String prediction = __Spreadsh_MLP_9_30_2(ContInputs);

                if(prediction.equals(stringStringMap.get("Class")))
                    TP.getAndIncrement();
                else
                    FP.getAndIncrement();

            });
            System.out.println("TP = " + TP.get());
            System.out.println("FP = " + FP.get());


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



//     if (args.length >= 9)
//
//     {
//
//       ContInputs[contID++] =  Double.parseDouble(args[argID++]);
//
//       ContInputs[contID++] =  Double.parseDouble(args[argID++]);
//
//       ContInputs[contID++] =  Double.parseDouble(args[argID++]);
//
//       ContInputs[contID++] =  Double.parseDouble(args[argID++]);
//
//       ContInputs[contID++] =  Double.parseDouble(args[argID++]);
//
//       ContInputs[contID++] =  Double.parseDouble(args[argID++]);
//
//       ContInputs[contID++] =  Double.parseDouble(args[argID++]);
//
//       ContInputs[contID++] =  Double.parseDouble(args[argID++]);
//
//       ContInputs[contID++] =  Double.parseDouble(args[argID++]);
//
//     }
//
//     else
//
//     {
//
//       String Comment = "";
//
//       String Comment1 = "**************************************************************************\n";
//
//       Comment += Comment1;
//
//       String Comment2 = "Please enter at least 9 command line parameters in the following order for \nthe program to Predict.\n";
//
//       Comment += Comment2;
//
//       Comment += Comment1;
//
//       String Comment3 = "J_EMB  Type - double (or) integer\n";
//
//       Comment += Comment3;
//
//       String Comment4 = "WTPT-5  Type - double (or) integer\n";
//
//       Comment += Comment4;
//
//       String Comment5 = "SC-3  Type - double (or) integer\n";
//
//       Comment += Comment5;
//
//       String Comment6 = "&lt;J_EMB&gt;  Type - double (or) integer\n";
//
//       Comment += Comment6;
//
//       String Comment7 = "&lt;SC-3&gt;  Type - double (or) integer\n";
//
//       Comment += Comment7;
//
//       String Comment8 = "&lt;WTPT-5&gt;  Type - double (or) integer\n";
//
//       Comment += Comment8;
//
//       String Comment9 = "DJ_EMB  Type - double (or) integer\n";
//
//       Comment += Comment9;
//
//       String Comment10 = "DSC-3  Type - double (or) integer\n";
//
//       Comment += Comment10;
//
//       String Comment11 = "DWTPT-5  Type - double (or) integer\n";
//
//       Comment += Comment11;
//
//       Comment += Comment1;
//
//       System.out.println(Comment);
//
//       System.exit(1);
//
//     }


    }


}

