package com.company.kimyouz.service.mapper;

import com.company.kimyouz.dto.request.RequestUserDto;
import com.company.kimyouz.dto.response.ResponseUserDto;
import com.company.kimyouz.entity.Authorities;
import com.company.kimyouz.entity.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.processing.Generated;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-27T21:03:16+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl extends UserMapper {

    @Override
    public User toEntity(RequestUserDto dto) {
        if ( dto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.firstname( dto.getFirstname() );
        user.lastname( dto.getLastname() );
        user.username( dto.getUsername() );
        user.age( dto.getAge() );

        user.enabled( false );
        user.password( passwordEncoder.encode(dto.getPassword()) );

        return user.build();
    }

    @Override
    public ResponseUserDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        ResponseUserDto.ResponseUserDtoBuilder responseUserDto = ResponseUserDto.builder();

        responseUserDto.userId( user.getUserId() );
        responseUserDto.firstname( user.getFirstname() );
        responseUserDto.lastname( user.getLastname() );
        responseUserDto.username( user.getUsername() );
        responseUserDto.password( user.getPassword() );
        responseUserDto.age( user.getAge() );
        responseUserDto.enabled( user.isEnabled() );
        List<Authorities> list = user.getAuthorities();
        if ( list != null ) {
            responseUserDto.authorities( new ArrayList<Authorities>( list ) );
        }
        responseUserDto.createdAt( user.getCreatedAt() );
        responseUserDto.updatedAt( user.getUpdatedAt() );
        responseUserDto.deletedAt( user.getDeletedAt() );

        return responseUserDto.build();
    }

    @Override
    public ResponseUserDto toDtoWithCard(User user) {
        if ( user == null ) {
            return null;
        }

        ResponseUserDto.ResponseUserDtoBuilder responseUserDto = ResponseUserDto.builder();

        responseUserDto.userId( user.getUserId() );
        responseUserDto.firstname( user.getFirstname() );
        responseUserDto.lastname( user.getLastname() );
        responseUserDto.username( user.getUsername() );
        responseUserDto.password( user.getPassword() );
        responseUserDto.age( user.getAge() );
        responseUserDto.enabled( user.isEnabled() );
        List<Authorities> list = user.getAuthorities();
        if ( list != null ) {
            responseUserDto.authorities( new ArrayList<Authorities>( list ) );
        }
        responseUserDto.createdAt( user.getCreatedAt() );
        responseUserDto.updatedAt( user.getUpdatedAt() );
        responseUserDto.deletedAt( user.getDeletedAt() );

        responseUserDto.cards( user.getCards().stream().map(this.cardMapper::toDto).collect(Collectors.toSet()) );

        return responseUserDto.build();
    }

    @Override
    public User updateUser(RequestUserDto dto, User user) {
        if ( dto == null ) {
            return user;
        }

        if ( dto.getFirstname() != null ) {
            user.setFirstname( dto.getFirstname() );
        }
        if ( dto.getLastname() != null ) {
            user.setLastname( dto.getLastname() );
        }
        if ( dto.getUsername() != null ) {
            user.setUsername( dto.getUsername() );
        }
        if ( dto.getPassword() != null ) {
            user.setPassword( dto.getPassword() );
        }
        if ( dto.getAge() != null ) {
            user.setAge( dto.getAge() );
        }

        return user;
    }

    @Override
    public User convertUsers(ResponseUserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.userId( userDto.getUserId() );
        user.firstname( userDto.getFirstname() );
        user.lastname( userDto.getLastname() );
        user.username( userDto.getUsername() );
        user.password( userDto.getPassword() );
        user.age( userDto.getAge() );
        user.authorities( grantedAuthorityCollectionToAuthoritiesList( userDto.getAuthorities() ) );
        user.createdAt( userDto.getCreatedAt() );
        user.updatedAt( userDto.getUpdatedAt() );
        user.deletedAt( userDto.getDeletedAt() );

        user.enabled( false );

        return user.build();
    }

    protected Authorities grantedAuthorityToAuthorities(GrantedAuthority grantedAuthority) {
        if ( grantedAuthority == null ) {
            return null;
        }

        Authorities.AuthoritiesBuilder authorities = Authorities.builder();

        authorities.authority( grantedAuthority.getAuthority() );

        return authorities.build();
    }

    protected List<Authorities> grantedAuthorityCollectionToAuthoritiesList(Collection<? extends GrantedAuthority> collection) {
        if ( collection == null ) {
            return null;
        }

        List<Authorities> list = new ArrayList<Authorities>( collection.size() );
        for ( GrantedAuthority grantedAuthority : collection ) {
            list.add( grantedAuthorityToAuthorities( grantedAuthority ) );
        }

        return list;
    }
}
