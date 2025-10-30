//package com.example.shipment_transmital_load_pdf.service;
//
//import com.example.shipment_transmital_load_pdf.entity.User;
//import com.example.shipment_transmital_load_pdf.repository.RoleRepository;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class RoleService {
//
//    private  final RoleRepository roleRepository;
//
//    public RoleService(RoleRepository roleRepository) {
//        this.roleRepository=roleRepository;
//    }
//
////    public List<User> findUsersByRole(String role, int pageNo, int pageSize){
////        Sort sort= Sort.by(Sort.Direction.ASC, "role_name");
////        Pageable pageable = PageRequest.of(pageNo-1, pageSize,sort);
////        return roleRepository.findAllUsersByRole(role,pageNo,pageSize);
////    }
//
//
//}
