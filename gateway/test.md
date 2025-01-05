
# Role-Based Access Control Workflow

## 1. Create Permissions

### Create `CREATE_PROJECT` Permission
```bash
curl -XPOST -H "Content-Type:application/json" http://localhost:8080/api/permissions -d '{"name":"CREATE_PROJECT", "description":"Permission to create a project"}'
```

### Create `DELETE_PROJECT` Permission
```bash
curl -XPOST -H "Content-Type:application/json" http://localhost:8080/api/permissions -d '{"name":"DELETE_PROJECT", "description":"Permission to delete a project"}'
```

### Create `MANAGE_USERS` Permission
```bash
curl -XPOST -H "Content-Type:application/json" http://localhost:8080/api/permissions -d '{"name":"MANAGE_USERS", "description":"Permission to manage users"}'
```

### Create `VIEW_PROJECT` Permission
```bash
curl -XPOST -H "Content-Type:application/json" http://localhost:8080/api/permissions -d '{"name":"VIEW_PROJECT", "description":"Permission to view a project"}'
```

### Create `EDIT_PROJECT` Permission
```bash
curl -XPOST -H "Content-Type:application/json" http://localhost:8080/api/permissions -d '{"name":"EDIT_PROJECT", "description":"Permission to edit a project"}'
```

## 2. Create Roles

### Create Admin Role
```bash
curl -XPOST -H "Content-Type:application/json" http://localhost:8080/api/roles -d '{"name":"Admin", "description":"Admin role"}'
```

### Create Viewer Role
```bash
curl -XPOST -H "Content-Type:application/json" http://localhost:8080/api/roles -d '{"name":"Viewer", "description":"Viewer role"}'
```

### Create Editor Role
```bash
curl -XPOST -H "Content-Type:application/json" http://localhost:8080/api/roles -d '{"name":"Editor", "description":"Editor role"}'
```

## 3. Assign Permissions to Roles

### Assign Permissions to Admin Role
```bash
curl -XPOST http://localhost:8080/api/roles/1/permissions/1
curl -XPOST http://localhost:8080/api/roles/1/permissions/2
curl -XPOST http://localhost:8080/api/roles/1/permissions/3
```

### Assign Permissions to Viewer Role
```bash
curl -XPOST http://localhost:8080/api/roles/2/permissions/4
```

### Assign Permissions to Editor Role
```bash
curl -XPOST http://localhost:8080/api/roles/3/permissions/4
curl -XPOST http://localhost:8080/api/roles/3/permissions/5
```

## 4. Create User Groups

### Create Developers Group
```bash
curl -XPOST -H "Content-Type:application/json" http://localhost:8080/api/user-groups -d '{"name":"Developers"}'
```

### Create Managers Group
```bash
curl -XPOST -H "Content-Type:application/json" http://localhost:8080/api/user-groups -d '{"name":"Managers"}'
```

### Create Guests Group
```bash
curl -XPOST -H "Content-Type:application/json" http://localhost:8080/api/user-groups -d '{"name":"Guests"}'
```

## 5. Assign Roles to User Groups

### Assign Editor Role to Developers Group
```bash
curl -XPOST http://localhost:8080/api/user-groups/1/roles/3
```

### Assign Admin Role to Managers Group
```bash
curl -XPOST http://localhost:8080/api/user-groups/2/roles/1
```

### Assign Viewer Role to Guests Group
```bash
curl -XPOST http://localhost:8080/api/user-groups/3/roles/2
```

## 6. Assign Users to User Groups

# Create Admin User
```bash
curl -XPOST http://localhost:8080/api/users \
-H "Content-Type: application/json" \
-d '{"username": "admin", "email": "admin@example.com"}'
```

# Create Developer User
```bash
curl -XPOST http://localhost:8080/api/users \
-H "Content-Type: application/json" \
-d '{"username": "developer", "email": "dev@example.com"}'
```
# Create Guest User
```bash
curl -XPOST http://localhost:8080/api/users \
-H "Content-Type: application/json" \
-d '{"username": "guest", "email": "guest@example.com"}'
```

### Add User to Developers Group
```bash
curl -XPOST http://localhost:8080/api/user-groups/1/users/1
```

### Add User to Managers Group
```bash
curl -XPOST http://localhost:8080/api/user-groups/2/users/2
```

### Add User to Guests Group
```bash
curl -XPOST http://localhost:8080/api/user-groups/3/users/3
```

## 7. Create an Organization

### Create Organization
```bash
curl -XPOST -H "Content-Type:application/json" http://localhost:8080/api/organizations -d '{"name":"Yalova University"}'
```

### Update Organization
```bash
curl -XPOST -H "Content-Type:application/json" http://localhost:8080/api/organizations -d '{"id":1,"name":"Yalova University update"}'
```

### Delete Organization
```bash
curl -XDELETE http://localhost:8080/api/projects/1
```

## 8. Create Projects for the Organization

### Create Project for Organization
```bash
curl -XPOST -H "Content-Type:application/json" http://localhost:8080/api/projects/organization/1 -d '{"name":"Project UBS"}'
```

### Update Project for Organization
```bash
curl -XPOST -H "Content-Type:application/json" http://localhost:8080/api/projects/organization/1 -d '{"id":1, "name":"Project UBS update"}'
```

### Delete Project
```bash
curl -XDELETE http://localhost:8080/api/projects/1
```

## 9. Assign User Groups to Projects

### Assign Developers and Managers to Project
```bash
curl -XPOST http://localhost:8080/api/projects/1/user-groups/1
curl -XPOST http://localhost:8080/api/projects/1/user-groups/2
```

### Assign Guests to View Project
```bash
curl -XPOST http://localhost:8080/api/projects/1/user-groups/3
```

## 10. Generate Report for Project
```bash
curl http://localhost:8080/api/reports/project/1
```
