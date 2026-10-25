
package graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import entities.Option;
import repository.OptionBusiness;
import repository.EtudiantBusiness;
import entities.Etudiant;

import java.util.List;

public class Query implements GraphQLQueryResolver {

    private OptionBusiness optionBusiness;
    private EtudiantBusiness etudiantBusiness;

    public Query(OptionBusiness optionBusiness,
                 EtudiantBusiness etudiantBusiness) {
        this.optionBusiness = optionBusiness;
        this.etudiantBusiness = etudiantBusiness;
    }

    // 1. Recuperer toutes les options
    public List<Option> getAllOptions() {
        return optionBusiness.getListeOptions();
    }

    // 2. Recuperer les options par domaine
    public List<Option> getOptionsByDomaine(String domaine) {
        return optionBusiness.getOptionsByDomaine(domaine);
    }

    // 3. Recuperer une option par son code
    public Option getOptionByCode(int codeOption) {
        return optionBusiness.getOptionByCode(codeOption);
    }

    // 4. Recuperer un etudiant par identifiant
    public Etudiant getEtudiantById(String identifiant) {
        return etudiantBusiness.getEtudiantByIdentifiant(identifiant);
    }

    // 5. Recuperer les etudiants par option
    public List<Etudiant> getEtudiantsByOption(int codeOption) {

        Option option = optionBusiness.getOptionByCode(codeOption);

        if (option == null) {
            return new java.util.ArrayList<>();
        }

        return etudiantBusiness.getEtudiantsByOption(option);
    }
}
