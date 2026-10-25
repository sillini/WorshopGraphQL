
package graphql;

import com.coxautodev.graphql.tools.SchemaParser;
import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLServlet;

import repository.OptionBusiness;
import repository.EtudiantBusiness;

import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndPoint extends SimpleGraphQLServlet {

    public GraphQLEndPoint() {
        super(buildSchema());
    }

    private static GraphQLSchema buildSchema() {

        OptionBusiness optionBusiness = new OptionBusiness();
        EtudiantBusiness etudiantBusiness = new EtudiantBusiness();

        return SchemaParser.newParser()
                .file("schema.graphql")
                .resolvers(
                        new Query(optionBusiness, etudiantBusiness),
                        new Mutations(optionBusiness, etudiantBusiness)
                )
                .build()
                .makeExecutableSchema();
    }
}
