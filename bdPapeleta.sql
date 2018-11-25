use master
go

if DB_ID('BDPapeleta') is not null
drop database BDPapeleta
go

create database BDPapeleta
go

use BDPapeleta
go


if OBJECT_ID('TB_Policia') is not null
drop table TB_Policia
go

create table TB_Policia (
	cod_pol varchar(6) not null primary key,
	ape_pol varchar(100) not null,
	dir_pol varchar(100) not null,
	tele_pol varchar(100) not null,
	mail_pol varchar(100) not null
)
go


if OBJECT_ID('TB_Infractor') is not null
drop table TB_Infractor
go

create table TB_Infractor (
	cod_infr varchar(6) not null primary key,
	ape_infr varchar(100) not null,
	dir_infr varchar(100) not null,
	tele_infr varchar(100) not null,
	mail_infr varchar(100) not null
)
go


if OBJECT_ID('TB_Papeleta') is not null
drop table TB_Papeleta
go

create table TB_Papeleta (
	cod_papeleta varchar(6) not null primary key,
	cod_pol varchar(6) not null,
	cod_infr varchar(6) not null,
	fecha date not null,
	monto money not null,
	descripcion varchar(255) not null,
	foreign key (cod_pol) references TB_Policia(cod_pol),
	foreign key (cod_infr) references TB_Infractor(cod_infr)
)
go

------------------------------------PROCEDIMIENTOS DE POLICÍA---------------------------------------------
if OBJECT_ID('USP_ListarPolicia') is not null
drop procedure USP_ListarPolicia
go

create procedure USP_ListarPolicia 
as
begin
	select cod_pol, ape_pol, dir_pol, tele_pol, mail_pol from TB_Policia
end
go


if OBJECT_ID('USP_BuscarPolicia') is not null
drop procedure USP_BuscarPolicia
go

create procedure USP_BuscarPolicia
(
	@cod varchar(6)
) 
as
begin
	select cod_pol, ape_pol, dir_pol, tele_pol, mail_pol from TB_Policia where cod_pol = @cod
end
go


if OBJECT_ID('USP_InsertarPolicia') is not null
drop procedure USP_InsertarPolicia
go

create procedure USP_InsertarPolicia
(
	@cod varchar(6),
	@ape varchar(100),
	@dir varchar(100),
	@tele varchar(100),
	@mail varchar(100)
) 
as
begin
	insert into TB_Policia(cod_pol, ape_pol, dir_pol, tele_pol, mail_pol) values (@cod, @ape, @dir, @tele, @mail)
end
go



if OBJECT_ID('USP_ModificarPolicia') is not null
drop procedure USP_ModificarPolicia
go

create procedure USP_ModificarPolicia
(
	@cod varchar(6),
	@ape varchar(100),
	@dir varchar(100),
	@tele varchar(100),
	@mail varchar(100)
) 
as
begin
	update TB_Policia set ape_pol = @ape, dir_pol = @dir, tele_pol = @tele, mail_pol = @mail where cod_pol = @cod
end
go


if OBJECT_ID('USP_EliminarPolicia') is not null
drop procedure USP_EliminarPolicia
go

create procedure USP_EliminarPolicia
(
	@cod varchar(6)
) 
as
begin
	delete from TB_Policia where cod_pol = @cod
end
go


----------------------------------------PROCEDIMIENTOS DE INFRACTOR-----------------------------------
if OBJECT_ID('USP_ListarInfractor') is not null
drop procedure USP_ListarInfractor
go

create procedure USP_ListarInfractor 
as
begin
	select cod_infr, ape_infr, dir_infr, tele_infr, mail_infr from TB_Infractor
end
go


if OBJECT_ID('USP_BuscarInfractor') is not null
drop procedure USP_BuscarInfractor
go

create procedure USP_BuscarInfractor
(
	@cod varchar(6)
) 
as
begin
	select cod_infr, ape_infr, dir_infr, tele_infr, mail_infr from TB_Infractor where cod_infr = @cod
end
go


if OBJECT_ID('USP_InsertarInfractor') is not null
drop procedure USP_InsertarInfractor
go

create procedure USP_InsertarInfractor
(
	@cod varchar(6),
	@ape varchar(100),
	@dir varchar(100),
	@tele varchar(100),
	@mail varchar(100)
) 
as
begin
	insert into TB_Infractor(cod_infr, ape_infr, dir_infr, tele_infr, mail_infr) values (@cod, @ape, @dir, @tele, @mail)
end
go



if OBJECT_ID('USP_ModificarInfractor') is not null
drop procedure USP_ModificarInfractor
go

create procedure USP_ModificarInfractor
(
	@cod varchar(6),
	@ape varchar(100),
	@dir varchar(100),
	@tele varchar(100),
	@mail varchar(100)
) 
as
begin
	update TB_Infractor set ape_infr = @ape, dir_infr = @dir, tele_infr = @tele, mail_infr = @mail where cod_infr = @cod
end
go


if OBJECT_ID('USP_EliminarInfractor') is not null
drop procedure USP_EliminarInfractor
go

create procedure USP_EliminarInfractor
(
	@cod varchar(6)
) 
as
begin
	delete from TB_Infractor where cod_infr = @cod
end
go



-------------------------------PROCEDIMIENTOS DE PAPELETA------------------------------------------------
if OBJECT_ID('USP_ListarPapeleta') is not null
drop procedure USP_ListarPapeleta
go

create procedure USP_ListarPapeleta 
as
begin
	select pa.cod_papeleta as pap, po.ape_pol as pol, inf.ape_infr as infr, pa.fecha as fecha, pa.monto as monto, pa.descripcion as descripcion
	from TB_Papeleta pa inner join TB_Policia po
	on pa.cod_pol = po.cod_pol inner join TB_Infractor inf 
	on pa.cod_infr = inf.cod_infr
end
go


if OBJECT_ID('USP_BuscarPapeleta') is not null
drop procedure USP_BuscarPapeleta
go

create procedure USP_BuscarPapeleta 
(
	@cod varchar(6)
)
as
begin
	select pa.cod_papeleta as pap, po.ape_pol as pol, inf.ape_infr as infr, pa.fecha as fecha, pa.monto as monto, pa.descripcion as descripcion
	from TB_Papeleta pa inner join TB_Policia po
	on pa.cod_pol = po.cod_pol inner join TB_Infractor inf 
	on pa.cod_infr = inf.cod_infr where pa.cod_papeleta = @cod
end
go



if OBJECT_ID('USP_InsertarPapeleta') is not null
drop procedure USP_InsertarPapeleta
go

create procedure USP_InsertarPapeleta
(
	@cod varchar(6),
	@pol varchar(6),
	@inf varchar(6),
	@fecha date,
	@monto money,
	@desc varchar(255)
) 
as
begin
	insert into TB_Papeleta(cod_papeleta, cod_pol, cod_infr, fecha, monto, descripcion) values (@cod, @pol, @inf, @fecha, @monto, @desc)
end
go



if OBJECT_ID('USP_ModificarPapeleta') is not null
drop procedure USP_ModificarPapeleta
go

create procedure USP_ModificarPapeleta
(
	@cod varchar(6),
	@pol varchar(6),
	@inf varchar(6),
	@fecha date,
	@monto money,
	@desc varchar(255)
) 
as
begin
	update TB_Papeleta set cod_pol = @pol, cod_infr = @inf, fecha = @fecha, monto = @monto, descripcion = @desc where cod_papeleta = @cod
end
go


if OBJECT_ID('USP_EliminarPapeleta') is not null
drop procedure USP_EliminarPapeleta
go

create procedure USP_EliminarPapeleta
(
	@cod varchar(6)
) 
as
begin
	delete from TB_Papeleta where cod_papeleta = @cod
end
go

---------------------------------------------------------------------------------------------------------

if OBJECT_ID('USP_PapeletaPol') is not null
drop procedure USP_PapeletaPol
go

create procedure USP_PapeletaPol
(
	@cod varchar(6)
) 
as
begin
	select pa.cod_papeleta as pap, po.ape_pol as pol, inf.ape_infr as infr, pa.fecha as fecha, pa.monto as monto, pa.descripcion as descripcion
	from TB_Papeleta pa inner join TB_Policia po
	on pa.cod_pol = po.cod_pol inner join TB_Infractor inf 
	on pa.cod_infr = inf.cod_infr where po.cod_pol = @cod
end
go


if OBJECT_ID('USP_PapeletaInfr') is not null
drop procedure USP_PapeletaInfr
go

create procedure USP_PapeletaInfr
(
	@cod varchar(6)
) 
as
begin
	select pa.cod_papeleta as pap, po.ape_pol as pol, inf.ape_infr as infr, pa.fecha as fecha, pa.monto as monto, pa.descripcion as descripcion
	from TB_Papeleta pa inner join TB_Policia po
	on pa.cod_pol = po.cod_pol inner join TB_Infractor inf 
	on pa.cod_infr = inf.cod_infr where inf.cod_infr = @cod
end
go
-----------------------------------------------------------------------------------------------------------

exec USP_InsertarPolicia 'POL001', 'Oscar Jimenez', 'Av. Velen 250', '985214789', 'ojim@gmail.com'
go

exec USP_InsertarPolicia 'POL002', 'Diego Gomez', 'Av. Dies Doce 45', '4715986', 'diGom@hotmail.com'
go

exec USP_InsertarPolicia 'POL003', 'Sara Ferrera', 'Jr. Guite 85', '952014786', 'saritaFer@yahoo.com'
go

exec USP_InsertarPolicia 'POL004', 'Diana Rosales', 'Calle Direma 63', '3697852', 'dianRosa@gmail.com'
go

exec USP_ListarPolicia
go

------------------------------------------------------------------------------------------------------------

exec USP_InsertarInfractor 'INF001', 'Jhon Mora', 'Av. Dies Doce 95', '963125870', 'jonMore@gmail.com'
go
exec USP_InsertarInfractor 'INF002', 'Dora Menguado', 'Av. Velen 215', '5201489', 'dorMen@gmail.com'
go
exec USP_InsertarInfractor 'INF003', 'Lisa Gutierrez', 'Jr. Mina 200', '6520148', 'lisGu@gmail.com'
go
exec USP_InsertarInfractor 'INF004', 'Mauricio Vela', 'Calle Benda 399', '951753820', 'mauVe@gmail.com'
go

exec USP_ListarInfractor
go

----------------------------------------------------------------------------------------------------------------
exec USP_ListarPapeleta
go


-----------------------------------------SIGUIENTE CODIGO---------------------------------------------------------

if OBJECT_ID('USP_NextCod') is not null
drop procedure USP_NextCod
go

create procedure USP_NextCod
(
	@tabla int,
	@next varchar(6) out
)
as
begin
	declare @cod int
	if @tabla = 0
	begin
		select @cod = CAST(substring(MAX(cod_pol),4,10) as int) + 1 from TB_Policia
		set @next = FORMAT( @cod, 'POL000')
	end
	else if @tabla = 1
	begin
		select @cod = CAST(substring(MAX(cod_infr),4,10) as int) + 1 from TB_Infractor
		set @next = FORMAT( @cod, 'INF000')
	end
	else if @tabla = 2
	begin
		select @cod = CAST(substring(MAX(cod_papeleta),4,10) as int) + 1 from TB_Papeleta
		set @next = FORMAT( @cod, 'PAP000')
	end
end
go

---------------------------------------------------------------------------------------------------------
declare @cod varchar(6)
exec USP_NextCod 0, @cod out
print @cod
go

----------------------------------------------------------------------------------------------------
if OBJECT_ID('USP_POLINFR') is not null
drop procedure USP_POLINFR
go

create procedure USP_POLINFR
as
begin
declare c_POLINFR cursor for
select pol.cod_pol, pol.ape_pol, pap.cod_papeleta, pap.fecha, pap.monto, inf.ape_infr from TB_Papeleta pap inner join TB_Policia pol
on pap.cod_pol = pol.cod_pol inner join TB_Infractor inf 
on pap.cod_infr = inf.cod_infr order by pol.cod_pol

declare @codpol varchar(6), @nompol varchar(100), @pap varchar(6), @fec date, @monto money, @inf varchar(100), @subtotal money = 0, @total money = 0, @temp varchar(6) = ''
open c_POLINFR
fetch c_POLINFR into @codpol ,@nompol, @pap, @fec, @monto, @inf
while @@FETCH_STATUS = 0
begin
	if @temp <> @codpol
	begin
		if @subtotal <> 0
		begin
			print space(30) + 'subtotal : ' + cast(@subtotal as varchar(100))
			set @subtotal = 0
		end
		print @codpol + ' ' + @nompol
	end
	print space(10) + @pap + ' ' + cast(@fec as varchar(100)) +  ' ' + cast(@monto as varchar(100)) + ' ' + @inf
	set @temp = @codpol
	set @subtotal += @monto
	set @total += @monto
	fetch c_POLINFR into @codpol,@nompol, @pap, @fec, @monto, @inf
end
print space(30) + 'subtotal : ' + cast(@subtotal as varchar(100))
print ''
print space(30) + 'TOTAL : ' + cast(@total as varchar(100))
close c_POLINFR
deallocate c_POLINFR
end
go

exec USP_POLINFR
go

---------------------------------------------------------------------------------------------------

if OBJECT_ID('USP_INFRPOL') is not null
drop procedure USP_INFRPOL
go

create procedure USP_INFRPOL
as
begin
declare c_INFRPOL cursor for
select inf.cod_infr, inf.ape_infr, pap.cod_papeleta, pap.fecha, pap.monto, pol.ape_pol from TB_Papeleta pap inner join TB_Policia pol
on pap.cod_pol = pol.cod_pol inner join TB_Infractor inf 
on pap.cod_infr = inf.cod_infr order by inf.cod_infr

declare @codinf varchar(6), @nominf varchar(100), @pap varchar(6), @fec date, @monto money, @pol varchar(100), @subtotal money = 0, @total money = 0, @temp varchar(6) = ''
open c_INFRPOL
fetch c_INFRPOL into @codinf ,@nominf, @pap, @fec, @monto, @pol
while @@FETCH_STATUS = 0
begin
	if @temp <> @codinf
	begin
		if @subtotal <> 0
		begin
			print space(30) + 'subtotal : ' + cast(@subtotal as varchar(100))
			set @subtotal = 0
		end
		print @codinf + ' ' + @nominf
	end
	print space(10) + @pap + ' ' + cast(@fec as varchar(100)) +  ' ' + cast(@monto as varchar(100)) + ' ' + @pol
	set @temp = @codinf
	set @subtotal += @monto
	set @total += @monto
	fetch c_INFRPOL into @codinf ,@nominf, @pap, @fec, @monto, @pol
end
print space(30) + 'subtotal : ' + cast(@subtotal as varchar(100))
print ''
print space(30) + 'TOTAL : ' + cast(@total as varchar(100))
close c_INFRPOL
deallocate c_INFRPOL
end
go

exec USP_INFRPOL
go

----------------------------------------------------------------------------------------------------------------------

if OBJECT_ID('USP_MantPol') is not null
drop procedure USP_MantPol
go

create procedure USP_MantPol
(
	@tipo int,
	@cod varchar(6) = null,
	@ape varchar(100) = null,
	@dir varchar(100) = null,
	@tele varchar(100) = null,
	@mail varchar(100) = null
)
as
	if @tipo = 0
		exec USP_InsertarPolicia @cod, @ape, @dir, @tele, @mail
	else if @tipo = 1
		exec USP_ModificarPolicia @cod, @ape, @dir, @tele, @mail
	else if @tipo = 2
		exec USP_EliminarPolicia @cod
	else if @tipo = 3
		exec USP_ListarPolicia
	else if @tipo = 4
		exec USP_BuscarPolicia @cod
go

exec USP_MantPol 4, 'POL001'
go

----------------------------------------------------------------------------------------------

if OBJECT_ID('USP_MantInf') is not null
drop procedure USP_MantInf
go

create procedure USP_MantInf
(
	@tipo int,
	@cod varchar(6) = null,
	@ape varchar(100) = null,
	@dir varchar(100) = null,
	@tele varchar(100) = null,
	@mail varchar(100) = null
)
as
	if @tipo = 0
		exec USP_InsertarInfractor @cod, @ape, @dir, @tele, @mail
	else if @tipo = 1
		exec USP_ModificarInfractor @cod, @ape, @dir, @tele, @mail
	else if @tipo = 2
		exec USP_EliminarInfractor @cod
	else if @tipo = 3
		exec USP_ListarInfractor
	else if @tipo = 4
		exec USP_BuscarInfractor @cod
go

exec USP_MantInf 4, 'INF001'
go

------------------------------------------------------------------------------------


if OBJECT_ID('USP_MantPap') is not null
drop procedure USP_MantPap
go

create procedure USP_MantPap
(
	@tipo int,
	@cod varchar(6) = null,
	@pol varchar(6) = null,
	@inf varchar(6) = null,
	@fecha date = null,
	@monto money = null,
	@desc varchar(255) = null
)
as
	if @tipo = 0
		exec USP_InsertarPapeleta @cod, @pol, @inf, @fecha, @monto, @desc
	else if @tipo = 1
		exec USP_ModificarPapeleta @cod, @pol, @inf, @fecha, @monto, @desc
	else if @tipo = 2
		exec USP_EliminarPapeleta @cod
	else if @tipo = 3
		exec USP_ListarPapeleta
	else if @tipo = 4
		exec USP_BuscarPapeleta @cod
go

exec USP_MantPap 4, 'PAP001'
go

-------------------------------------------------------------------------------------------

