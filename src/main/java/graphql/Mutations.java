
package graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import entities.Option;
import entities.Etudiant;
import repository.OptionBusiness;
import repository.EtudiantBusiness;

public class Mutations implements GraphQLMutationResolver {

    private OptionBusiness optionBusiness;
    private EtudiantBusiness etudiantBusiness;

    public Mutations(OptionBusiness optionBusiness,
                     EtudiantBusiness etudiantBusiness) {
        this.optionBusiness = optionBusiness;
        this.etudiantBusiness = etudiantBusiness;
    }

    // 1. Ajouter une option
    public boolean addOption(int codeOption, String libelle,
                             String domaine, String responsable,
                             int credits, int semestre, int capacite) {

        Option option = new Option(
                codeOption, libelle, domaine, responsable,
                credits, semestre, capacite
        );

        return optionBusiness.addOption(option);
    }

    // 2. Modifier une option
    public boolean updateOption(int codeOption, String libelle,
                                String domaine, String responsable,
                                int credits, int semestre, int capacite) {

        Option option = new Option(
                codeOption, libelle, domaine, responsable,
                credits, semestre, capacite
        );

        return optionBusiness.updateOption(codeOption, option);
    }

    // 3. Supprimer une option
    public boolean deleteOption(int codeOption) {
        return optionBusiness.deleteOption(codeOption);
    }

    // 4. Ajouter un étudiant
    public boolean addEtudiant(String identifiant, String nom,
                               String prenom, int codeOption,
                               int anneeEtude, String email) {

        Option option = optionBusiness.getOptionByCode(codeOption);

        if (option == null) {
            return false;
        }

        Etudiant etudiant = new Etudiant(
                identifiant, nom, prenom,
                option, anneeEtude, email
        );

        return etudiantBusiness.addEtudiant(etudiant);
    }

    // 5. Modifier un étudiant
    public boolean updateEtudiant(String identifiant, String nom,
                                  String prenom, int codeOption,
                                  int anneeEtude, String email) {

        Option option = optionBusiness.getOptionByCode(codeOption);

        if (option == null) {
            return false;
        }

        Etudiant etudiant = new Etudiant(
                identifiant, nom, prenom,
                option, anneeEtude, email
        );

        return etudiantBusiness.updateEtudiant(
                identifiant, etudiant
        );
    }

    // 6. Supprimer un étudiant
    public boolean deleteEtudiant(String identifiant) {
        return etudiantBusiness.deleteEtudiant(identifiant);
    }
}
