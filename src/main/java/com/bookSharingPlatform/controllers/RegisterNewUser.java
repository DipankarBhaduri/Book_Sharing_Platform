package com.bookSharingPlatform.controllers;

import com.bookSharingPlatform.models.ApplicationUser;
import com.bookSharingPlatform.repositories.CustomRepositoryHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.naming.Reference;
import java.util.List;

@RestController
@RequestMapping("/user")
@Component
public class RegisterNewUser {

    @Autowired
    private CustomRepositoryHandler customRepositoryHandler ;

    @GetMapping("/add")
    public ResponseEntity<String> addNewApplicationUser(@RequestBody ApplicationUser applicationUser) {
        customRepositoryHandler.saveEntity(applicationUser);
        return new ResponseEntity<>(applicationUser.getName()+" Saved Successfully" , HttpStatus.CREATED) ;
    }
}

/*

package com.elabs.main.models;

        import com.tech.medicea.core.models.generic.BaseEntity;
        import lombok.AllArgsConstructor;
        import lombok.Getter;
        import lombok.NoArgsConstructor;
        import lombok.Setter;
        import org.springframework.data.mongodb.core.mapping.Document;
        import javax.naming.Reference;
        import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "group_access_request")
public class GroupAccessRequest extends BaseEntity {
    private List<Reference> raiseRequestFor ;
    private List<Reference> groups ;
    private String requestDescription ;
}


package com.elabs.main.models;

        import com.tech.medicea.core.models.generic.BaseEntity;
        import lombok.AllArgsConstructor;
        import lombok.Getter;
        import lombok.NoArgsConstructor;
        import lombok.Setter;
        import org.springframework.data.mongodb.core.mapping.Document;
        import javax.naming.Reference;
        import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "group_of_group_access_request")
public class GroupOfGroupAccessRequest extends BaseEntity {
    private List<Reference> groups ;
    private List<Reference> groupOfGroup ;
    private String requestDescription ;
}



 */

/*
TEST CASE FOR PERMISSION MODULE








//package com.tech.medicea.core.services.handlers.permission;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.gson.Gson;
//import com.tech.medicea.core.database.morphia.ApplicationContextEventTestsAppConfig;
//import com.tech.medicea.core.database.morphia.CustomMappingMongoConverter;
//import com.tech.medicea.core.models.generic.ApplicationUser;
//import com.tech.medicea.core.models.generic.Menu;
//import com.tech.medicea.core.models.permissions.appPermissions.*;
//import com.tech.medicea.core.services.handlers.TemplateHandler;
//import com.tech.medicea.core.services.repository.CollectionHandler;
//import com.tech.medicea.core.services.service.UserRestService;
//import com.tech.medicea.core.util.CommonUtil;
//import com.tech.medicea.core.util.DBInteraction.CollectionQuery;
//import com.tech.medicea.core.util.Reference;
//import org.junit.Assert;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.mongodb.core.MongoOperations;
//import org.springframework.messaging.converter.JsonbMessageConverter;
//
//import java.util.*;
//import java.util.stream.Collectors;
//import static com.mongodb.internal.connection.tlschannel.util.Util.assertTrue;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotEquals;
//
//@SpringBootTest(classes = {PermissionHandler.class, ObjectMapper.class , Gson.class ,
//        CustomMappingMongoConverter.class , CollectionHandler.class , MongoOperations.class ,
//        ApplicationContextEventTestsAppConfig.class , TemplateHandler.class , CommonUtil.class , JsonbMessageConverter.class})
//public class PermissionHandlerTest {
//
//    Logger logger = LoggerFactory.getLogger( UserRestService.class );
//
//    @Autowired
//    private PermissionHandler permissionHandler;
//
//    @Autowired
//    private CollectionHandler collectionHandler;
//
//
//    @Test
//    public void testGetPermissionLists() throws Exception {
//
////        String userid = "61b7274b028d301ed0b07772" ;
////        try {
////            List<HashMap<String, AppModule>> listResponse = permissionHandler.getPermissionLists(userid) ;
////            TreeMap<String, AppModule> mapResponse = permissionHandler.getPermissionLists1(userid) ;
////            Assert.assertTrue(listResponse != null );
////            Assert.assertTrue(mapResponse != null );
////        } catch ( Exception e ){
////            logger.info("permissionHandler.getPermissionList");
////        }
//    }
//
//    @Test
//    public void testserMetaData() {
//
//        ApplicationUser applicationUser = new ApplicationUser() ;
//        applicationUser.setAppId("TEST");
//        applicationUser.setRefCode("TEST01");
//        applicationUser.set_id("1234567890");
//
//        UserMetaData userMetadata = permissionHandler.getUserMetaData(applicationUser);
//        assertTrue(userMetadata.getAppId().equals("TEST"));
//        assertTrue(userMetadata.getRefCode().equals("TEST01"));
//        Assert.assertTrue("1234567890".equals(userMetadata.getUserId()));
//    }
//
//    @Test
//    public void testGetApplicationUsersFromUsersGroupAndGroupOfGroupWithNeitherAppApplicationUserNorAppUserGroupNull() {
//        // Create the input object
//        AppRoleBindingSubject appRoleBindingSubject = new AppRoleBindingSubject();
//        AppGroupOfGroup transientAppGroupOfGroup = new AppGroupOfGroup();
//        AppUsersGroup transientAppUsersGroup1 = new AppUsersGroup();
//        AppUsersGroup transientAppUsersGroup2 = new AppUsersGroup();
//        ApplicationUser transientApplicationUser1 = new ApplicationUser();
//        ApplicationUser transientApplicationUser2 = new ApplicationUser();
//        transientAppUsersGroup1.setTransientApplicationUser(Arrays.asList(transientApplicationUser1));
//        transientAppUsersGroup2.setTransientApplicationUser(Arrays.asList(transientApplicationUser2));
//        transientAppGroupOfGroup.setTransientAppUsersGroup(Arrays.asList(transientAppUsersGroup1, transientAppUsersGroup2));
//        appRoleBindingSubject.setTransientAppGroupOfGroup(transientAppGroupOfGroup);
//
//        HashSet<ApplicationUser> result = permissionHandler.getApplicationUsersFromUsersGroupAndGroupOfGroup(appRoleBindingSubject);
//
//        assertEquals(2, result.size());
//        assertTrue(result.contains(transientApplicationUser1));
//        assertTrue(result.contains(transientApplicationUser2));
//    }
//
//    @Test
//    public void testGetApplicationUsersFromUsersGroupAndGroupOfGroup_withAppGroupOfGroup() {
//        // Arrange
//        AppRoleBindingSubject appRoleBindingSubject = new AppRoleBindingSubject();
//        AppGroupOfGroup appGroupOfGroup = new AppGroupOfGroup();
//        List<AppUsersGroup> appUsersGroups = new ArrayList<>();
//        AppUsersGroup appUsersGroup1 = new AppUsersGroup();
//        appUsersGroup1.setTransientApplicationUser(List.of(new ApplicationUser()));
//        appUsersGroups.add(appUsersGroup1);
//        AppUsersGroup appUsersGroup2 = new AppUsersGroup();
//        appUsersGroup2.setTransientApplicationUser(List.of(new ApplicationUser(), new ApplicationUser()));
//        appUsersGroups.add(appUsersGroup2);
//        appGroupOfGroup.setTransientAppUsersGroup(appUsersGroups);
//        appRoleBindingSubject.setTransientAppGroupOfGroup(appGroupOfGroup);
//
//        // Act
//        HashSet<ApplicationUser> result = permissionHandler.getApplicationUsersFromUsersGroupAndGroupOfGroup(appRoleBindingSubject);
//
//        // Assert
//        Assertions.assertEquals(3, result.size());
//        Assertions.assertTrue(result.containsAll(appUsersGroup1.getTransientApplicationUser()));
//        Assertions.assertTrue(result.containsAll(appUsersGroup2.getTransientApplicationUser()));
//    }
//
//    @Test
//    public void testGetAppUsersGroup() {
//        Reference applicationUser1 = new Reference() ;
//        applicationUser1.set_id("61b7274b028d301ed0b07771");
//        Reference applicationUser2 = new Reference() ;
//        applicationUser2.set_id("61b7274b028d301ed0b07772");
//        Reference applicationUser3 = new Reference() ;
//        applicationUser3.set_id("61b7274b028d301ed0b07773");
//        Reference applicationUser4 = new Reference() ;
//        applicationUser4.set_id("61b7274b028d301ed0b07774");
//
//        AppUsersGroup appUsersGroup1 = new AppUsersGroup() ;
//        AppUsersGroup appUsersGroup2 = new AppUsersGroup() ;
//        AppUsersGroup appUsersGroup3 = new AppUsersGroup() ;
//
//        AppUserApprover appUserApprover1 = new AppUserApprover() ;
//        appUserApprover1.setAppUser(applicationUser1);
//
//        AppUserApprover appUserApprover2 = new AppUserApprover() ;
//        appUserApprover1.setAppUser(applicationUser2);
//
//        AppUserApprover appUserApprover3 = new AppUserApprover() ;
//        appUserApprover1.setAppUser(applicationUser2);
//
//        AppUserApprover appUserApprover4 = new AppUserApprover() ;
//        appUserApprover1.setAppUser(applicationUser2);
//
//        appUsersGroup1.setAppUserApproverList(List.of(appUserApprover1,appUserApprover2,appUserApprover4));
//        appUsersGroup2.setAppUserApproverList(List.of(appUserApprover1,appUserApprover3));
//        appUsersGroup3.setAppUserApproverList(List.of(appUserApprover1,appUserApprover4));
//
//        List<AppUsersGroup> appUsersGroups = List.of(appUsersGroup1,appUsersGroup2,appUsersGroup3) ;
//        ApplicationUser applicationUser = new ApplicationUser() ;
//        applicationUser.set_id(applicationUser1.get_id());
//
//        try {
//            List < AppUsersGroup > result = permissionHandler.getAppUsersGroups(appUsersGroups,applicationUser) ;
//            Assert.assertTrue(result==null );
//        } catch ( Exception e ){
//            logger.info("permissionHandler.getAppUsersGroups");
//        }
//    }
//
//    @Test
//    void testGetAppGroupOfGroups() {
//        // Create the necessary mock objects
//        List<AppGroupOfGroup> appGroupOfGroupList = new ArrayList<>();
//        List<AppUsersGroup> appUsersGroupList = new ArrayList<>();
//
//        // Create the sample data
//        Reference applicationUser1 = new Reference();
//        applicationUser1.set_id("61b7274b028d301ed0b07771");
//        Reference applicationUser2 = new Reference();
//        applicationUser2.set_id("61b7274b028d301ed0b07772");
//        Reference applicationUser3 = new Reference();
//        applicationUser3.set_id("61b7274b028d301ed0b07773");
//        Reference applicationUser4 = new Reference();
//        applicationUser4.set_id("61b7274b028d301ed0b07774");
//
//        AppUsersGroup appUsersGroup1 = new AppUsersGroup();
//        AppUsersGroup appUsersGroup2 = new AppUsersGroup();
//        AppUsersGroup appUsersGroup3 = new AppUsersGroup();
//
//        AppUserApprover appUserApprover1 = new AppUserApprover();
//        appUserApprover1.setAppUser(applicationUser1);
//
//        AppUserApprover appUserApprover2 = new AppUserApprover();
//        appUserApprover2.setAppUser(applicationUser2);
//
//        AppUserApprover appUserApprover3 = new AppUserApprover();
//        appUserApprover3.setAppUser(applicationUser2);
//
//        AppUserApprover appUserApprover4 = new AppUserApprover();
//        appUserApprover4.setAppUser(applicationUser2);
//
//        appUsersGroup1.set_id("groupId1"); // Set the group ID
//        appUsersGroup1.setAppUserApproverList(List.of(appUserApprover1, appUserApprover2, appUserApprover4));
//
//        appUsersGroup2.set_id("groupId2"); // Set the group ID
//        appUsersGroup2.setAppUserApproverList(List.of(appUserApprover1, appUserApprover3));
//
//        appUsersGroup3.set_id("groupId3"); // Set the group ID
//        appUsersGroup3.setAppUserApproverList(List.of(appUserApprover1, appUserApprover4));
//
//        appUsersGroupList.add(appUsersGroup1);
//        appUsersGroupList.add(appUsersGroup2);
//        appUsersGroupList.add(appUsersGroup3);
//
//        AppGroupOfGroup appGroupOfGroup = new AppGroupOfGroup();
//        appGroupOfGroupList.add(appGroupOfGroup);
//        ApplicationUser user = new ApplicationUser() ;
//
//        try {
//        // Call the method to be tested
//        List<AppGroupOfGroup> result = permissionHandler.getAppGroupOfGroups(appGroupOfGroupList, appUsersGroupList, user);
//
//        // Assert the expected result
//        Assertions.assertEquals(1, result.size()) ;
//        } catch ( Exception e ){
//            logger.info("permissionHandler.getAppGroupOfGroups");
//        }
//    }
//
//    @Test
//    void testGetRoleBindingList() {
//        List<AppRoleBinding> appRoleBindingList = permissionHandler.getRoleBindingList() ;
//        Assert.assertTrue(appRoleBindingList != null );
//    }
//
//    @Test
//    void testGetUserMetaData() {
//        ApplicationUser applicationUser = new ApplicationUser() ;
//        applicationUser.setAppId("APPID");
//        applicationUser.setRefCode("REFCODE");
//        applicationUser.set_id("123");
//        UserMetaData userMetadata = permissionHandler.getUserMetaData(applicationUser);
//        assertTrue(userMetadata.getAppId().equals("APPID"));
//    }
//
//    @Test
//    void testGetMenuList (){
//        HashSet<Reference> menuReferenceHashSet1 = new HashSet<>() ;
//        Reference menu = new Reference() ;
//        menu.setName("menu");
//        menu.set_id("618415dbbbd19278af0295b9");
//
//        Reference menu2 = new Reference() ;
//        menu.setName("menu2");
//        menu.set_id("6184301c1529d275fcda9fee");
//
//        menuReferenceHashSet1.add(menu2) ;
//        menuReferenceHashSet1.add(menu) ;
//
//        try {
//            List<Menu> menuList1 = permissionHandler.getMenuList(menuReferenceHashSet1) ;
//            Assert.assertTrue(menuList1.size() == 2 );
//        } catch ( Exception e ){
//            logger.info("permissionHandler.getMenuList");
//        }
//    }
//
//    @Test
//    void testGetAppTemplateTab ( ){
//        AppTemplateTab appTemplateTab = null ;
//        App_Resource appResource = new App_Resource() ;
//        appResource.setView(true);
//        appResource.setEdit(true);
//        appResource.setAdd(true);
//
//        AppTemplateTab appTemplateTab1 = new AppTemplateTab() ;
//        App_Resource appResource1 = new App_Resource() ;
//        appResource1.setView(true);
//        appResource1.setEdit(true);
//        appResource1.setAdd(true);
//        appResource1.setDelete(true);
//        appResource1.setAuditHistory(true);
//
//        AppTemplateTab ans = permissionHandler.getAppTemplateTab(appTemplateTab ,appResource) ;
//        List<AppActions> list = ans.getAccess() ;
//        assertEquals( list.size(), 5);
//
//        AppTemplateTab ans1 = permissionHandler.getAppTemplateTab(appTemplateTab1 ,appResource1) ;
//        List<AppActions> list1 = ans1.getAccess() ;
//        assertNotEquals(list1.size(), 3);
//    }
//
//
//    @Test
//    void testGetAppRoleReferences (){
//        String userid = "61b7274b028d301ed0b07772" ;
//
//        List<AppRoleBinding> appRoleBindingList = collectionHandler.findAllDocumentsUsingCache(CollectionQuery.builder()
//                .clazz(AppRoleBinding.class)
//                .build());
//
//        List<AppGroupOfGroup> appGroupOfGroupList = collectionHandler.findAllDocumentsUsingCache(CollectionQuery.builder()
//                .clazz(AppGroupOfGroup.class)
//                .build());
//
//        List<AppUsersGroup> appUsersGroupList = collectionHandler.findAllDocumentsUsingCache(CollectionQuery.builder()
//                .clazz(AppUsersGroup.class)
//                .build());
//
//        List<Reference> referenceList = permissionHandler.getAppRoleReferences
//                (userid, appRoleBindingList ,appGroupOfGroupList ,appUsersGroupList) ;
//
//        assertNotEquals(referenceList.size() , 0 );
//    }
//
//    @Test
//    void testGetAppUsersGroups() {
//        Reference applicationUser1 = new Reference() ;
//        applicationUser1.set_id("1234");
//        applicationUser1.setName("Dipankar");
//
//        Reference applicationUser2 = new Reference() ;
//        applicationUser2.set_id("12");
//        applicationUser2.setName("Shivam");
//
//        Reference applicationUser3 = new Reference() ;
//        applicationUser3.setName("Keshav");
//        applicationUser3.set_id("123");
//
//        AppUserApprover appUserApprover1 = new AppUserApprover() ;
//        appUserApprover1.setAppUser(applicationUser1);
//
//        AppUserApprover appUserApprover2 = new AppUserApprover() ;
//        appUserApprover2.setAppUser(applicationUser2);
//
//        AppUserApprover appUserApprover3 = new AppUserApprover() ;
//        appUserApprover3.setAppUser(applicationUser3);
//
//        AppUsersGroup appUsersGroup1 = new AppUsersGroup() ;
//        appUsersGroup1.setAppUserApproverList(List.of(appUserApprover1,appUserApprover2,appUserApprover3));
//
//        ApplicationUser applicationUser = new ApplicationUser() ;
//        applicationUser.setName("Dipankar");
//        applicationUser.set_id("1234");
//
//        List<AppUsersGroup> ans = permissionHandler.getAppUsersGroups(List.of(appUsersGroup1) , applicationUser) ;
//        Assert.assertEquals(1, ans.size());
//    }
//
//    @Test
//    void testPopulateTransientAppGroupOfGroupForAppRoleBindingSubject () {
//        List<AppUsersGroup> appUsersGroupList = collectionHandler.findAllDocumentsUsingCache(CollectionQuery.builder()
//                .clazz(AppUsersGroup.class)
//                .build());
//
//        List<AppGroupOfGroup> appGroupOfGroupList = collectionHandler.findAllDocumentsUsingCache(CollectionQuery.builder()
//                .clazz(AppGroupOfGroup.class)
//                .build());
//
//        Map<String, AppGroupOfGroup> appGroupOfGroupMap = appGroupOfGroupList.stream()
//                .collect(Collectors
//                        .toMap(AppGroupOfGroup::get_id , appGroupOfGroup -> appGroupOfGroup));
//
//        List<ApplicationUser> appApplicationUserList = collectionHandler.findAllDocumentsUsingCache(CollectionQuery.builder()
//                .clazz(ApplicationUser.class)
//                .build());
//
//         AppGroupOfGroup appGroupOfGroup1 = appGroupOfGroupList.get(0) ;
//
//        AppRoleBindingSubject appRoleBindingSubject = new AppRoleBindingSubject() ;
//        appRoleBindingSubject.setTransientAppGroupOfGroup(appGroupOfGroup1);
//
//       // permissionHandler.populateTransientAppGroupOfGroupForAppRoleBindingSubject(appUsersGroupList ,appGroupOfGroupMap ,appApplicationUserList , appRoleBindingSubject);
//        assertTrue(appRoleBindingSubject.getTransientAppGroupOfGroup() != null) ;
//    }
//}
 */