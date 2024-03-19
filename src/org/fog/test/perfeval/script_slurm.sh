#!/bin/bash
#SBATCH --job-name=slurm_ifogsim_java
#SBATCH --output=slurm_ifogsim_java_2500.out
#SBATCH --error=slurm_ifogsim_java.err
#SBATCH --nodes=1
#SBATCH --cpus-per-task=16
#SBATCH --mem=16G
#SBATCH --time=20:00:00
#SBATCH --partition=longq
#SBATCH --priority=1

# Compilation du code source Java

# acceder au file 

cd /home/d.aitomar/IdeaProjects/Ifogsim_Kmeans_SOM-main/src/org/fog/test/perfeval  
 
export PATH=/cm/shared/apps/eb/software/Java/11.0.20/bin:$PATH

# javac 
#javac -version

echo "Compiling" 
javac -cp "/home/d.aitomar/IdeaProjects/Ifogsim_Kmeans_SOM-main/src:/home/d.aitomar/IdeaProjects/Ifogsim_Kmeans_SOM-main/src/org:/home/d.aitomar/IdeaProjects/Ifogsim_Kmeans_SOM-main/jars/poi/*:/home/d.aitomar/IdeaProjects/Ifogsim_Kmeans_SOM-main/jars/commons-math3-3.5/*:/home/d.aitomar/IdeaProjects/Ifogsim_Kmeans_SOM-main/jars/*:/home/d.aitomar/IdeaProjects/Ifogsim_Kmeans_SOM-main/dataset/newDataSet*:/home/d.aitomar/IdeaProjects/Ifogsim_Kmeans_SOM-main/dataset/newDataSet/user/*:/home/d.aitomar/IdeaProjects/Ifogsim_Kmeans_SOM-main/dataset/*" -Xlint:deprecation -Xlint:unchecked /home/d.aitomar/IdeaProjects/Ifogsim_Kmeans_SOM-main/src/org/fog/test/perfeval/TranslationServiceFog_RandomMobility_Clustering_SOM.java


# Exécution de l'application Java
echo "Execution"
java -cp "/home/d.aitomar/IdeaProjects/Ifogsim_Kmeans_SOM-main/src:/home/d.aitomar/IdeaProjects/Ifogsim_Kmeans_SOM-main/src/org:/home/d.aitomar/IdeaProjects/Ifogsim_Kmeans_SOM-main/jars/poi/*:/home/d.aitomar/IdeaProjects/Ifogsim_Kmeans_SOM-main/jars/commons-math3-3.5/*:/home/d.aitomar/IdeaProjects/Ifogsim_Kmeans_SOM-main/jars/*:/home/d.aitomar/IdeaProjects/Ifogsim_Kmeans_SOM-main/dataset/newDataSet*:/home/d.aitomar/IdeaProjects/Ifogsim_Kmeans_SOM-main/dataset/newDataSet/user/*:/home/d.aitomar/IdeaProjects/Ifogsim_Kmeans_SOM-main/dataset/*" org.fog.test.perfeval.TranslationServiceFog_RandomMobility_Clustering_SOM | tee resultat_slurm_2500_USER.txt




