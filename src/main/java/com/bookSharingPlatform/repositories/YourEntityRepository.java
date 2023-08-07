package com.bookSharingPlatform.repositories;
import com.bookSharingPlatform.models.ApplicationUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YourEntityRepository<T, ID> extends MongoRepository<T, ID> {
    default ApplicationUser findByEmail(String email) {
        // Implementation goes here (Spring Data JPA automatically handles the query based on method name)
        // You can even use Lambda Expressions for simple queries
        return null ;
    }

    default ApplicationUser findByName(String name) {
        // Implementation goes here (Spring Data JPA automatically handles the query based on method name)
        return null;
    }
}



        /*
        List<Template> newResult = new ArrayList<>() ;
        Map<String, HashSet<String>> userIdTemplateTabSet = permissionHandler.getUserIdAndTemplateTabIdSetMap();
        if ( result != null && result.size() == 1 && employee != null && result.get(0) instanceof Template) {
            HashSet<String> hashSetWithTemplateTabSet = userIdTemplateTabSet.get(employee.get_id());
            if ( hashSetWithTemplateTabSet == null ){
                permissionHandler.getPermissionLists(employee) ;
                userIdTemplateTabSet = permissionHandler.getUserIdAndTemplateTabIdSetMap();
                hashSetWithTemplateTabSet = userIdTemplateTabSet.get(employee.get_id());
            }
            Template template ;
            template = (Template) result.get(0);
            List<TemplateTab> allTemplateTabList = template.getTemplateTabs();
            List<Reference> allTemplateTabReferenceList = template.getTabs();
            template.setTemplateTabs(null);
            template.setTabs(null);

            if (allTemplateTabList != null ){
                HashSet<String> finalHashSetWithTemplateTabSet = hashSetWithTemplateTabSet;
                List<TemplateTab> templateTabListWithPermission = allTemplateTabList.stream()
                        .filter(templateTab -> finalHashSetWithTemplateTabSet.contains(templateTab.get_id()))
                        .collect(Collectors.toList());
                template.setTemplateTabs(templateTabListWithPermission);
            }

            if (allTemplateTabList != null){
                HashSet<String> finalHashSetWithTemplateTabSet1 = hashSetWithTemplateTabSet;
                List<Reference> templateTabReferenceListWithPermission = allTemplateTabReferenceList.stream()
                        .filter(reference -> finalHashSetWithTemplateTabSet1.contains(reference.get_id()))
                        .collect(Collectors.toList());
                template.setTabs(templateTabReferenceListWithPermission);
            }
            newResult.add(template) ;
            return newResult ;
        }

         */