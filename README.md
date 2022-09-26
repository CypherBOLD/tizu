# alteracoes

## Terraform

### variables.tf - editado:
```
 variable "usuario" {
     default = "tizu"
}
```

### securitygroup.tf - adicionado:
```

#SG de permitindo o acesso a porta 80 para toda a internet, 
#pois quem for acessar nosso front, estara na internet
resource "aws_security_group" "sg_acesso_tomcat_publico" {
  description = "sg acesso tomcat publico"
  vpc_id = aws_vpc.vpc.id
  #liberando a entrada pela porta 80 - HTTP
  ingress {
    description      = "Tomcat"
    from_port        = 8080
    to_port          = 8080
    protocol         = "tcp"
    cidr_blocks      = [var.bloco_ip_destino_publico]
  }
  tags = {
    "Name" = "${var.usuario}-sg-tomcat-publico"
  }
}

#SG de permitindo o acesso a porta 80 para toda a internet, 
#pois quem for acessar nosso front, estara na internet
resource "aws_security_group" "sg_acesso_mysql_publico" {
  description = "sg acesso mysql publico"
  vpc_id = aws_vpc.vpc.id
  #liberando a entrada pela porta 80 - HTTP
  ingress {
    description      = "MySQL"
    from_port        = 3306
    to_port          = 3306
    protocol         = "tcp"
    cidr_blocks      = [var.bloco_ip_destino_publico]
  }
  tags = {
    "Name" = "${var.usuario}-sg-mysql-publico"
  }
}

resource "aws_security_group" "sg_acesso_portainer_publico" {
  description = "sg acesso portainer publico"
  vpc_id = aws_vpc.vpc.id
  #liberando a entrada pela porta 80 - HTTP
  ingress {
    description      = "Portainer 8000"
    from_port        = 8000
    to_port          = 8000
    protocol         = "tcp"
    cidr_blocks      = [var.bloco_ip_destino_publico]
  }
  ingress {
    description      = "Portainer 9443"
    from_port        = 9443
    to_port          = 9443
    protocol         = "tcp"
    cidr_blocks      = [var.bloco_ip_destino_publico]
  }
  tags = {
    "Name" = "${var.usuario}-sg-portainer-publico"
  }
}
```

### ec2.tf - editado:
editado a `linha 25`, para adicionar as regras criadas no arquivo `securitygroup.tf`
```  
vpc_security_group_ids = ["${aws_security_group.sg_acesso_ssh_local.id}","${aws_security_group.sg_acesso_web_publico.id}","${aws_security_group.sg_acesso_tomcat_publico.id}","${aws_security_group.sg_acesso_mysql_publico.id}","${aws_security_group.sg_acesso_portainer_publico.id}" ]
```

## Ansible
- destrinchado o playbook em mais arquivos,
`playbook.yaml`, `backdocker.yaml` e adicionado a aplicacao `portainer.yaml` !

### Inventory
- Trocado o hostname (backend/frontend)
> alterar os ip das instancias

### playbook.yaml
- instala todas as dependencias e sobe o container `mysql` no `backend`
> alterar o diretorio padrao nas linhas : 70, 81 e 87

### backdocker.yaml
- Sobe o container da aplicacao java no `backend`
>  alterar o nome da pasta na linha 8, para a que esta sendo usada no `playbook` linha 70
```       chdir: /home/ubuntu/tizu ```

### Portainer.yaml
- Instala o portainer para voce gerenciar os containers da instancia pelo navegador
> Para acessar, digitar `http:// ip da instancia :9443`

# Comandos Utilizados na maquina de gerenciamento
- Copiar a pasta ansible na maquina de gerenciamento, (pode usar o vscode, conectado por ssh)

- entrar na pasta ansible
```
cd ansible/
```

- instala as dependencias e e sobe o mysql
```
 ansible-playbook playbook.yaml -i inventory
```

- conectar o container do mysql no workbench pelo ip `ip_da_instancia:3306` , e criar as tabelas e adicionar os dados, com o script `create.sql` no repo `main`

- subir o container do backend depois de o container do mysql estar online
```  
ansible-playbook backdocker.yaml -i inventory 
```