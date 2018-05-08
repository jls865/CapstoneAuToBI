package LingoPros;

import java.io.*;
import java.lang.*;

public class autobiRunner
{
    private String inputFile;
    private String outputFile;
    private String analyzer;
    private String model;

    /*
    * Command Line Arguments: 0-AudioFile, 1-OutputFile(path?),
    * 2-Model, 3-Analyzer
    */
    public static void main(String[] args)
    {
        autobiRunner ar = new autobiRunner();
        
        ar.setInputAudioFile(args[0]);
        ar.setOutputFile(args[1]);
        ar.setModel(args[2],args[3]);
        ar.setAnalyzer(args[3]);
        ar.runAutobiAnalysis();
        
        
        System.out.println(ar.analyzer);
        System.out.println(ar.model);
        
    }
    
    //checks to make sure input is valid then sets it if it is
    public void setInputAudioFile(String file)
    {
        if(file.contains(".wav"))
        {
            this.inputFile= file;
        }
        else
        {
            System.out.println("ERROR: input file must be of type .wav");
            System.exit(1);
        }
        
    }
    
    //checks to make sure outputfile is valid then sets it if so
    public void setOutputFile(String file)
    {
        if(file.contains(".arff"))
        {
            this.outputFile= file;
        }
        else
        {
            System.out.println("ERROR: output file must be of type .arff");
            System.exit(1);
        }

    }
    
    
    public void setModel(String num, String temp)
    {
        //make sure model is an integer
        try
        {
            int models = Integer.parseInt(num);
        }
        catch(NumberFormatException e)
        {
            System.out.println("ERROR: model must be an integer");
            System.exit(0);
        }
        
        //if it is reassign it
        int models = Integer.parseInt(num);
        
        //check if anlysis is an integer
        try
        {
            int analysis = Integer.parseInt(temp);
        }
        catch(NumberFormatException e)
        {
            System.out.println("ERROR: analysis must be an integer");
            System.exit(1);
        }
        
        //if it is reassign it
        int analysis = Integer.parseInt(temp);
        
        
        if (models== 0)
        {
            this.model = "bdc_burnc.";
        }
        else
        {
            System.out.println("ERROR: model must be 0(BURNC)\n");
            System.exit(1);
        }
        
        //check analysis to see which suffix to use
        if (analysis == 0)
        {
            this.model += "pabt.classification.model";
        }
        else if (analysis== 1)
        {
            this.model += "interp.detection.model";
        }
        else if (analysis== 2)
        {
            this.model += "intonp.detection.model";
        }
        else if (analysis== 3)
        {
            this.model += "phacc.classification.model";
        }
        else if (analysis== 4)
        {
            this.model += "bdc_burnc.acc.detection.model";
        }
        else if (analysis== 5)
        {
            this.model += "bdc_burnc.acc.classification.model";
        }
        
        //error if it is not one of the 6 analyses
        else
        {
            System.out.println("ERROR: analysis must be 0,1,2,3,4,5:");
            System.out.println("0 = boundary_tone_classifier");
            System.out.println("1 = intonational_phrase_boundary_detector");
            System.out.println("2 = intermediate_phrase_boundary_detector");
            System.out.println("3 = phrase_accent_classifier");
            System.out.println("4 = pitch_accent_detector");
            System.out.println("5 = pitch_accent_classifier");
            System.exit(1);
        }
        
        
    
    }
    
    
    public void setAnalyzer(String num)
    {
        int analysis = Integer.parseInt(num);
        if (analysis== 0)
        {
            this.analyzer = "boundary_tone_classifier";
        }
        else if (analysis== 1)
        {
            this.analyzer = "intermediate_phrase_boundary_detector";
        }
        else if (analysis== 2)
        {
            this.analyzer = "intonational_phrase_boundary_detector";
        }
        else if (analysis== 3)
        {
            this.analyzer = "phrase_accent_classifier";
        }
        else if (analysis== 4)
        {
            this.analyzer = "pitch_accent_detector";
        }
        else if (analysis== 5)
        {
            this.analyzer = "pitch_accent_classifier";
        } 

    }
    
    
    public void runAutobiAnalysis()
    {
        File autobi_directory = new File("/lib/");
        String total = "java -jar AuToBI.jar ";
        total += ("-wav_file=" + this.inputFile);
        total += (" -arff_file=" + this.outputFile);
        total += (" -" + this.analyzer + "=" + this.model); 
        System.out.println(total);
        String[] cmdList = total.split(" ");
        
        ProcessBuilder pb = new ProcessBuilder(cmdList);
        pb.directory(autobi_directory);
        pb.inheritIO();
        pb.command(cmdList);
        try
        {
            Process p = pb.start();
        }
        catch(IOException e)
        {
            System.out.println("ERROR: Something broke: " + e);
        }
    
    }
    
}
    
