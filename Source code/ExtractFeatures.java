package panos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

import distances.EuclideanDistance;
import filters.blur.GaussianBlur;
import filters.blur.MeanBlur;
import filters.blur.ShakeEffect;
import filters.border.GaborFilter;
import filters.border.GaussianGradient;
import filters.border.HessianFilter;
import filters.border.HighOrderGradient;
import filters.border.LaplacianFilter;
import filters.border.LinearGradient;
import filters.border.RodriguesSobel;
import filters.matrices.CoOccurrenceFilter;
import filters.misc.EntropyFilter;
import filters.misc.FrangiFilter;
import filters.misc.VesselnessFilter;
import filters.morphology.Closing;
import filters.morphology.Opening;
import filters.noise.AnisotropicDiffusion;
import filters.noise.KuwaharaFilter;
import filters.sharpen.EnhanceSharpAreas;
import filters.sharpen.SimpleSharpen;
import image.Image;

public class ExtractFeatures {

	private static void pr(String str){
		System.out.println(str);
	}
	
	public static void main(String[] args) throws Exception{
		
		/*
		 String folder = "D:/Documentos/Panos/Primeiro Teste/Teste com Numeros Pequenos/";
		 Image angio1 = new Image(folder + "angio1.png");
		 Image angio1Class = new Image(folder + "angio1classe.png");
		 */
		
		 
		 String folder = "D:/Documentos/Panos/Base Retina/OSIRIX/GRUSELAMBIX Images/";
		 Image angio2 = new Image(folder + "IMG-0007-00001.jpg");
		 Image angio2Class = new Image(folder + "Felipe Segmentacao/IMG-0007-00001.png");
		 
		
		/*
		 String folder = "D:/Documentos/Panos/Base Retina/DRIVE/DRIVE/test/";
		 Image angio2 = new Image(folder + "images/20_test.tif");
		 Image angio2Class = new Image(folder + "1st_manual/20_manual1.gif");
		 */
		 
		 Image rAngio = angio2,
				 rClass = angio2Class;
		 
		 rAngio.convertToGray(0);
		
		 
		 //gaussian
		 pr("Gaussian..");
		 GaussianBlur gBlur1 = new GaussianBlur(),
				 gBlur2 = new GaussianBlur();
		 
		 gBlur2.setKernelSize(25);
		 gBlur2.setSpreadX(3); gBlur2.setSpreadY(3);
		 
		 Image g1Image = rAngio.clone().applyFilter(gBlur1),
				 g2Image = rAngio.clone().applyFilter(gBlur2);
		 
		 //linear gradient
		 pr("Linear Gradient");
		 LinearGradient lGradient = new LinearGradient();
		 
		 Image lGradientImage = rAngio.clone().applyFilter(lGradient);
		 
		 //gaussian gradient
		 pr("Gaussian Gradient");
		 GaussianGradient gaussGradient1 = new GaussianGradient(),
				 gaussGradient2 = new GaussianGradient();
		 
		 gaussGradient2.setAmplitude(4);
		 gaussGradient2.setSpreadX(3);
		 gaussGradient2.setKernelSize(30);
		 
		 Image gaussGradient1Image = rAngio.clone().applyFilter(gaussGradient1),
				 gaussGradient2Image = rAngio.clone().applyFilter(gaussGradient2);
		 
		 //hessian features
		 pr("Hessian Features");
		 HessianFilter h1 = new HessianFilter(),
				 h2 = new HessianFilter(), 
				 h3 = new HessianFilter(),
				 h4 = new HessianFilter(),
				 h5 = new HessianFilter(),
				 h6 = new HessianFilter(),
				 h7 = new HessianFilter(),
				 h8 = new HessianFilter(),
				 h9 = new HessianFilter(),
				 h10 = new HessianFilter(),
				 h11 = new HessianFilter();
		 
		 h1.setOperationType(HessianFilter.HessianOperationType.TYPE_DETERMINANT);
		 h2.setOperationType(HessianFilter.HessianOperationType.TYPE_ELEMENT0_0);
		 h3.setOperationType(HessianFilter.HessianOperationType.TYPE_ELEMENT0_1);
		 h4.setOperationType(HessianFilter.HessianOperationType.TYPE_ELEMENT1_0);
		 h5.setOperationType(HessianFilter.HessianOperationType.TYPE_ELEMENT1_1);
		 h6.setOperationType(HessianFilter.HessianOperationType.TYPE_FIRST_EIGENVALUE);
		 h7.setOperationType(HessianFilter.HessianOperationType.TYPE_GAMMA_NORM_SQUARE_EIGENVALUE_DIFFERENCE);
		 h8.setOperationType(HessianFilter.HessianOperationType.TYPE_MODULE);
		 h9.setOperationType(HessianFilter.HessianOperationType.TYPE_ORIENTATION);
		 h10.setOperationType(HessianFilter.HessianOperationType.TYPE_SECOND_EIGENVALUE);
		 h11.setOperationType(HessianFilter.HessianOperationType.TYPE_TRACE);
		 
		 Image h1Image = rAngio.clone().applyFilter(h1),
				 h2Image = rAngio.clone().applyFilter(h2),
				 h3Image = rAngio.clone().applyFilter(h3),
				 h4Image = rAngio.clone().applyFilter(h4),
				 h5Image = rAngio.clone().applyFilter(h5),
				 h6Image = rAngio.clone().applyFilter(h6),
				 h7Image = rAngio.clone().applyFilter(h7),
				 h8Image = rAngio.clone().applyFilter(h8),
				 h9Image = rAngio.clone().applyFilter(h9),
				 h10Image = rAngio.clone().applyFilter(h10),
				 h11Image = rAngio.clone().applyFilter(h11);
		 
		 //subtraction of gaussians
		 pr("Subtraction of Gaussians");
		 EnhanceSharpAreas eSharp1 = new EnhanceSharpAreas(),
				 eSharp2 = new EnhanceSharpAreas();
		 
		 eSharp2.setKernelSize(25);
		 eSharp2.setSpreadX(2); eSharp2.setSpreadY(2);
		 eSharp2.setDifferenceX(1); eSharp2.setDifferenceY(1);
		 
		 Image eSharp1Image = rAngio.clone().applyFilter(eSharp1),
				 eSharp2Image = rAngio.clone().applyFilter(eSharp2);
		 
		 //mean, max, etc
		 pr("Mean, max, etc");
		 MeanBlur mBlur1 = new MeanBlur(),
				 mBlur2 = new MeanBlur(),
				 mBlur3 = new MeanBlur(),
				 mBlur4 = new MeanBlur(),
				 mBlur5 = new MeanBlur();
		 
		 mBlur1.setOperationType(MeanBlur.AverageType.TYPE_ARITHMETIC_MEAN);
		 mBlur2.setOperationType(MeanBlur.AverageType.TYPE_GEOMETRIC_MEAN);
		 mBlur3.setOperationType(MeanBlur.AverageType.TYPE_MAX);
		 mBlur4.setOperationType(MeanBlur.AverageType.TYPE_MEDIAN);
		 mBlur5.setOperationType(MeanBlur.AverageType.TYPE_MIN);
		 
		 Image mBlur1Image = rAngio.clone().applyFilter(mBlur1),
				 mBlur2Image = rAngio.clone().applyFilter(mBlur2),
				 mBlur3Image = rAngio.clone().applyFilter(mBlur3),
				 mBlur4Image = rAngio.clone().applyFilter(mBlur4),
				 mBlur5Image = rAngio.clone().applyFilter(mBlur5);
		 
		 
		 //anisotroppic diffusion
		 pr("Anisotr. Diff");
		 AnisotropicDiffusion ad1 = new AnisotropicDiffusion(),
				 ad2 = new AnisotropicDiffusion(),
				 ad3 = new AnisotropicDiffusion(),
				 ad4 = new AnisotropicDiffusion();
		 
		 ad1.setIterations(10);
		 ad1.setKappa(3);
		 ad1.setLambda(0.5f);
		 ad2.setIterations(20);
		 ad2.setKappa(4);
		 ad2.setLambda(0.3f);
		 ad3.setIterations(40);
		 ad3.setKappa(6);
		 ad3.setLambda(0.8f);
		 ad4.setIterations(35);
		 ad4.setKappa(3);
		 ad4.setLambda(2f);
		 
		 Image ad1Image = rAngio.clone().applyFilter(ad1),
				 ad2Image = rAngio.clone().applyFilter(ad2),
				 ad3Image = rAngio.clone().applyFilter(ad3),
				 ad4Image = rAngio.clone().applyFilter(ad4);
		 
		 
		 //opening
		 pr("Opening");
		 Opening op1 = new Opening(),
				 op2 = new Opening(),
				 op3 = new Opening();
		 
		 op1.setStructuringElement(Opening.STRUCT_PRIMARY);
		 op1.setTimesToDilate(1); op1.setTimesToErode(1);
		 op2.setStructuringElement(Opening.STRUCT_PRIMARY);
		 op2.setTimesToDilate(2); op2.setTimesToErode(3);
		 op3.setStructuringElement(Opening.STRUCT_FILLED_RING);
		 op3.setTimesToDilate(1); op3.setTimesToErode(1);
		 
		 Image op1Image = rAngio.clone().applyFilter(op1),
				 op2Image = rAngio.clone().applyFilter(op2),
				 op3Image = rAngio.clone().applyFilter(op3);
		 
		 
		 //closing
		 pr("Closing");
		 Closing cl1 = new Closing(),
				 cl2 = new Closing(),
				 cl3 = new Closing();
		 
		 cl1.setStructuringElement(Closing.STRUCT_PRIMARY);
		 cl1.setTimesToDilate(1); cl1.setTimesToErode(1);
		 cl2.setStructuringElement(Closing.STRUCT_PRIMARY);
		 cl2.setTimesToDilate(2); cl2.setTimesToErode(3);
		 cl3.setStructuringElement(Closing.STRUCT_FILLED_RING);
		 cl3.setTimesToDilate(1); cl3.setTimesToErode(1);
		 
		 Image cl1Image = rAngio.clone().applyFilter(cl1),
				 cl2Image = rAngio.clone().applyFilter(cl2),
				 cl3Image = rAngio.clone().applyFilter(cl3);
		 
		 //kuwahara
		 pr("Kuwahara");
		 KuwaharaFilter ku1 = new KuwaharaFilter(),
				 ku2 = new KuwaharaFilter();
		 
		 ku1.setKernelSize(10);
		 ku2.setKernelSize(20);
		 
		 Image ku1Image = rAngio.clone().applyFilter(ku1),
				 ku2Image = rAngio.clone().applyFilter(ku2);
		 
		 //gabor filter
		 pr("Gabor Filter");
		 GaborFilter gFil1 = new GaborFilter(),
				 gFil2 = new GaborFilter(),
				 gFil3 = new GaborFilter(),
				 gFil4 = new GaborFilter();
		 
		 gFil1.setKernelSize(7);
		 gFil1.setOrientation(0);
		 gFil2.setKernelSize(7);
		 gFil2.setOrientation(45);
		 gFil3.setKernelSize(7);
		 gFil3.setOrientation(90);
		 gFil4.setKernelSize(7);
		 gFil4.setOrientation(135);
		 
		 Image gFil1Image = rAngio.clone().applyFilter(gFil1),
				 gFil2Image = rAngio.clone().applyFilter(gFil2),
				 gFil3Image = rAngio.clone().applyFilter(gFil3),
				 gFil4Image = rAngio.clone().applyFilter(gFil4);
		 
		 //high order derivatives
		 pr("High order derivatives");
		 HighOrderGradient hod1 = new HighOrderGradient(),
				 hod2 = new HighOrderGradient(),
				 hod3 = new HighOrderGradient(),
				 hod4 = new HighOrderGradient();
		 
		 hod1.setGradientOrder(2);
		 hod2.setGradientOrder(3);
		 hod3.setGradientOrder(5);
		 hod4.setGradientOrder(8);
		 
		 Image hod1Image = rAngio.clone().applyFilter(hod1),
				 hod2Image = rAngio.clone().applyFilter(hod2),
				 hod3Image = rAngio.clone().applyFilter(hod3),
				 hod4Image = rAngio.clone().applyFilter(hod4);
		 
		 //entropy
		 pr("Entropy");
		 EntropyFilter en1 = new EntropyFilter(),
				 en2 = new EntropyFilter(),
				 en3 = new EntropyFilter();
		 
		 en1.setNumOfChunks(16);
		 en2.setNumOfChunks(64);
		 en3.setNumOfChunks(128);
		 
		 Image en1Image = rAngio.clone().applyFilter(en1),
				 en2Image = rAngio.clone().applyFilter(en2),
				 en3Image = rAngio.clone().applyFilter(en3);
		 
		 //laplacian
		 pr("Laplacian");
		 LaplacianFilter lap1 = new LaplacianFilter(),
				 lap2 = new LaplacianFilter();
		 
		 lap2.setKernelSize(20);
		 lap2.setSpreadX(2);
		 lap2.setSpreadY(2);
		 
		 Image lap1Image = rAngio.clone().applyFilter(lap1),
				 lap2Image = rAngio.clone().applyFilter(lap2);
		 
		 //rodriguessobel
		 pr("Rodrigues sobel");
		 RodriguesSobel rSob1 = new RodriguesSobel(),
				 rSob2 = new RodriguesSobel();
		 
		 rSob1.setDistance(2);
		 rSob2.setDistance(5);
		 
		 Image rSob1Image = rAngio.clone().applyFilter(rSob1),
				 rSob2Image = rAngio.clone().applyFilter(rSob2);
		 
		 //shake effect
		 pr("Shake eff");
		 ShakeEffect se1 = new ShakeEffect();
		 
		 Image se1Image = rAngio.clone().applyFilter(se1);
		 
		 //sharpen
		 pr("Sharpen");
		 SimpleSharpen simpleS1 = new SimpleSharpen();
		 
		 Image simpleS1Image = rAngio.clone().applyFilter(simpleS1);
		 
		 //co-occurrence
		 pr("Co-occurrence");
		 CoOccurrenceFilter co1 = new CoOccurrenceFilter(),
				 co2 = new CoOccurrenceFilter(),
				 co3 = new CoOccurrenceFilter(),
				 co4 = new CoOccurrenceFilter(),
				 co5 = new CoOccurrenceFilter(),
				 co6 = new CoOccurrenceFilter();
		 
		 co1.setDelta(0, 1);
		 co2.setDelta(1, 0);
		 co3.setDelta(1, 1);
		 co4.setDelta(0, 1);
		 co4.setOperationType(CoOccurrenceFilter.TYPE_ENTROPY);
		 co5.setDelta(1, 0);
		 co5.setOperationType(CoOccurrenceFilter.TYPE_ENTROPY);
		 co6.setDelta(1, 1);
		 co6.setOperationType(CoOccurrenceFilter.TYPE_ENTROPY);
		 
		 Image co1Image = rAngio.clone().applyFilter(co1),
				 co2Image = rAngio.clone().applyFilter(co2),
				 co3Image = rAngio.clone().applyFilter(co3),
				 co4Image = rAngio.clone().applyFilter(co4),
				 co5Image = rAngio.clone().applyFilter(co5),
				 co6Image = rAngio.clone().applyFilter(co6);
		 
		 //frangi
		 pr("frangi");
		 FrangiFilter frangi1 = new FrangiFilter(),
				 frangi2 = new FrangiFilter(),
				 frangi3 = new FrangiFilter();
		 
		 frangi1.setSigmaStart(1);frangi1.setSigmaEnd(1);
		 frangi2.setSigmaStart(1); frangi2.setSigmaEnd(2);
		 frangi3.setSigmaEnd(2); frangi3.setSigmaEnd(3);
		 
		 Image frangi1Image = rAngio.clone().applyFilter(frangi1),
				 frangi2Image = rAngio.clone().applyFilter(frangi2),
				 frangi3Image = rAngio.clone().applyFilter(frangi3);
		 
		 //vesselness
		 pr("Vesselness");
		 VesselnessFilter v1 = new VesselnessFilter(),
				 v2 = new VesselnessFilter();
		 
		 v2.setScale(0.8f);
		 
		 Image v1Image = rAngio.clone().applyFilter(v1),
				 v2Image = rAngio.clone().applyFilter(v2);
		 
		 
		 //cooccurrence of the hessian
		 pr("Coocc of Hessian");
		 CoOccurrenceFilter coHess1 = new CoOccurrenceFilter(),
				 coHess2 = new CoOccurrenceFilter(),
				 coHess3 = new CoOccurrenceFilter(),
				 coHess4 = new CoOccurrenceFilter(),
				 coHess5 = new CoOccurrenceFilter(),
				 coHess6 = new CoOccurrenceFilter();

		 coHess1.setDelta(0, 1);
		 coHess2.setDelta(1, 0);
		 coHess3.setDelta(1, 1);
		 coHess4.setDelta(0, 1);
		 coHess4.setOperationType(CoOccurrenceFilter.TYPE_ENERGY);
		 coHess5.setDelta(1, 0);
		 coHess5.setOperationType(CoOccurrenceFilter.TYPE_ENERGY);
		 coHess6.setDelta(1, 1);
		 coHess6.setOperationType(CoOccurrenceFilter.TYPE_ENERGY);
		 
		 Image coHess1Image = h10Image.clone().applyFilter(coHess1),
				 coHess2Image = h10Image.clone().applyFilter(coHess2),
				 coHess3Image = h10Image.clone().applyFilter(coHess3),
				 coHess4Image = h10Image.clone().applyFilter(coHess4),
				 coHess5Image = h10Image.clone().applyFilter(coHess5),
				 coHess6Image = h10Image.clone().applyFilter(coHess6),
				 
				 coHess7Image = h11Image.clone().applyFilter(coHess1),
				 coHess8Image = h11Image.clone().applyFilter(coHess2),
				 coHess9Image = h11Image.clone().applyFilter(coHess3),
				 coHess10Image = h11Image.clone().applyFilter(coHess4),
				 coHess11Image = h11Image.clone().applyFilter(coHess5),
				 coHess12Image = h11Image.clone().applyFilter(coHess6);
		 
		 //class related
		 pr("Class related");
		 
		 
		 
		 
		 
		 
		 File outFile = new File(folder + "out.txt");
		 bw = new BufferedWriter(new FileWriter(outFile));
		 
		 
		 for (int i=0; i<rAngio.getHeight(); i++){
			 for (int j=0; j<rAngio.getWidth(); j++){
				 write(rAngio.getPixel(j, i) );
				 
				 write(g1Image.getPixel(j, i) );
				 write(g2Image.getPixel(j, i) );
				 
				 write(lGradientImage.getPixel(j, i) );
				 
				 write(gaussGradient1Image.getPixel(j, i) );
				 write(gaussGradient2Image.getPixel(j, i) );
				 
				 write(h1Image.getPixel(j, i) );
				 write(h2Image.getPixel(j, i) );
				 write(h3Image.getPixel(j, i) );
				 write(h4Image.getPixel(j, i) );
				 write(h5Image.getPixel(j, i) );
				 write(h6Image.getPixel(j, i) );
				 write(h7Image.getPixel(j, i) );
				 write(h8Image.getPixel(j, i) );
				 write(h9Image.getPixel(j, i) );
				 write(h10Image.getPixel(j, i) );
				 write(h11Image.getPixel(j, i) );
				 
				 write(eSharp1Image.getPixel(j, i) );
				 write(eSharp2Image.getPixel(j, i) );
				 
				 write(mBlur1Image.getPixel(j, i) );
				 write(mBlur2Image.getPixel(j, i) );
				 write(mBlur3Image.getPixel(j, i) );
				 write(mBlur4Image.getPixel(j, i));
				 write(mBlur5Image.getPixel(j, i) );
				 
				 write(ad1Image.getPixel(j, i) );
				 write(ad2Image.getPixel(j, i) );
				 write(ad3Image.getPixel(j, i) );
				 write(ad4Image.getPixel(j, i) );
				 
				 write(op1Image.getPixel(j, i) );
				 write(op2Image.getPixel(j, i) );
				 write(op3Image.getPixel(j, i) );
				 
				 write(cl1Image.getPixel(j, i) );
				 write(cl2Image.getPixel(j, i) );
				 write(cl3Image.getPixel(j, i) );
				 
				 write(ku1Image.getPixel(j, i) );
				 write(ku2Image.getPixel(j, i) );
				 
				 write(gFil1Image.getPixel(j, i) );
				 write(gFil2Image.getPixel(j, i) );
				 write(gFil3Image.getPixel(j, i) );
				 write(gFil4Image.getPixel(j, i) );
				 
				 write(hod1Image.getPixel(j, i) );
				 write(hod2Image.getPixel(j, i) );
				 write(hod3Image.getPixel(j, i) );
				 write(hod4Image.getPixel(j, i) );
				 
				 write(en1Image.getPixel(j, i) );
				 write(en2Image.getPixel(j, i) );
				 write(en3Image.getPixel(j, i) );
				 
				 write(lap1Image.getPixel(j, i) );
				 write(lap2Image.getPixel(j, i) );
				 
				 write(rSob1Image.getPixel(j, i) );
				 write(rSob2Image.getPixel(j, i) );
				 
				 write(se1Image.getPixel(j, i) );
				 
				 write(simpleS1Image.getPixel(j, i) );
				 
				 write(co1Image.getPixel(j, i) );
				 write(co2Image.getPixel(j, i) );
				 write(co3Image.getPixel(j, i) );
				 write(co4Image.getPixel(j, i) );
				 write(co5Image.getPixel(j, i) );
				 write(co6Image.getPixel(j, i) );
				 
				 write(frangi1Image.getPixel(j, i) );
				 write(frangi2Image.getPixel(j, i) );
				 write(frangi3Image.getPixel(j, i) );
				 
				 write(v1Image.getPixel(j, i) );
				 write(v2Image.getPixel(j, i) );
				 
				 write(coHess1Image.getPixel(j, i) );
				 write(coHess2Image.getPixel(j, i) );
				 write(coHess3Image.getPixel(j, i) );
				 write(coHess4Image.getPixel(j, i) );
				 write(coHess5Image.getPixel(j, i) );
				 write(coHess6Image.getPixel(j, i) );
				 write(coHess7Image.getPixel(j, i) );
				 write(coHess8Image.getPixel(j, i) );
				 write(coHess9Image.getPixel(j, i) );
				 write(coHess10Image.getPixel(j, i) );
				 write(coHess11Image.getPixel(j, i) );
				 write(coHess12Image.getPixel(j, i) );
				 
				 //class related
				 final int multSize = 1;
				 int[] diag = new int[multSize], hor = new int[multSize], ver = new int[multSize];
				 for (int d=1; d<= multSize; d++){
					 if (j - d >= 0)
						 if (rClass.getPixel(j - d, i) > 150) hor[d-1]=1;
					 if (j + d < rAngio.getWidth())
						 if (rClass.getPixel(j + d, i) > 150) hor[d-1]=1;
					 
					 if (i - d >= 0)
						 if (rClass.getPixel(j, i - d) > 150) ver[d-1]=1;
					 if (i + d < rAngio.getHeight())
						 if (rClass.getPixel(j, i + d) > 150) ver[d-1]=1;
					 
					 if (i - d >= 0 && j - d >= 0)
						 if (rClass.getPixel(j - d, i - d) > 150) diag[d-1]=1;
					 if (i - d >= 0 && j + d < rAngio.getWidth())
						 if (rClass.getPixel(j + d, i - d) > 150) diag[d-1]=1;
					 if (i + d < rAngio.getHeight() && j - d >= 0)
						 if (rClass.getPixel(j - d, i + d) > 150) diag[d-1]=1;
					 if (i + d < rAngio.getHeight() && j + d < rAngio.getWidth())
						 if (rClass.getPixel(j + d, i + d) > 150) diag[d-1]=1;
					 
				 }
				 //radius
				 int thereis = 0;
				 double closestDist = Integer.MAX_VALUE;
				 final int radius = 7;
				 EuclideanDistance euDist = new EuclideanDistance();
				 for (int i2=i-radius; i2<=i+radius; i2++){
					 for (int j2=j-radius; j2<=j+radius; j2++){
						 if (i2 < 0 || j2 < 0 || i2 >=rAngio.getHeight() || j2 >= rAngio.getWidth()) continue;
						 final double cDist = euDist.compute(j, i, j2, i2);
						 if (cDist > radius) continue;
						 if (rClass.getPixel(j2, i2) < 150) continue;
						 if (j == j2 && i == i2) continue;
						 
						 if (cDist < closestDist){
							 closestDist = cDist;
							 thereis = 1;
						 }
						 
					 }
				 }
				 
				 write(diag[0]);
				 write(hor[0]);
				 write(ver[0]);
				 write(thereis);
				 write(closestDist);
				 
				 //class
				 bw.write((rClass.getPixel(j, i) > 150 ? 1 : 0) + "");
				 bw.newLine();
			 }
		 }
		 
		 bw.flush();
		 bw.close();
		 
	}
	
	static BufferedWriter bw;
	
	private static void write(double number) throws IOException{
		bw.write(String.format(Locale.ROOT,"%.2f,", number));
		//bw.write(number + ",");
	}
	
}
